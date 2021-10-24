package com.adjust.adjusthomework.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.adjust.adjusthomework.R;
import com.adjust.adjusthomework.base.BaseActivity;
import com.adjust.adjusthomework.data.model.SecondResponse;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showLoading(Boolean show) {

    }

    @Override
    public void showError(int message) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showMessage(int message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void showResult(SecondResponse second) {

    }

    @Override
    public void fadeProgressBar() {

    }
}