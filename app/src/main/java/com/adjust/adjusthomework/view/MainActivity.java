package com.adjust.adjusthomework.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adjust.adjusthomework.R;
import com.adjust.adjusthomework.base.BaseActivity;
import com.adjust.adjusthomework.data.model.SecondResponse;

import java.util.Calendar;

public class MainActivity extends BaseActivity implements MainContract.View {

    private MainPresenter mPresenter;
    private TextView responseId;
    private TextView responseSecond;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseId = findViewById(R.id.textView);
        responseSecond = findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar);

        mPresenter = new MainPresenter(this);

        findViewById(R.id.button).setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            Calendar cal = Calendar.getInstance();
            int second = cal.get(Calendar.SECOND);
            mPresenter.sendSelectedSecond(second);
        });
    }


    @Override
    public void showResult(SecondResponse second) {
        progressBar.setVisibility(View.GONE);
        responseId.setText("id: " + second.getId());
        responseSecond.setText("second: " + second.getSeconds());
    }

    @Override
    public void fadeProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sharedPreferences != null)
            mPresenter.cacheQueueValues(sharedPreferences);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPreferences != null){
            progressBar.setVisibility(View.VISIBLE);
            mPresenter.loadAndHandleCachedQueues(sharedPreferences);
        }
    }

}