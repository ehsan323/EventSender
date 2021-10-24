package com.adjust.adjusthomework.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.BaseView {

    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
    }

    @Override
    public void showLoading(Boolean show) {

    }

    @Override
    public void showError(int message) {

    }

    @Override
    public void showError(String message) {
        getProgressBar().setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int message) {

    }

    @Override
    public void showMessage(String message) {
        getProgressBar().setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public abstract ProgressBar getProgressBar();

}
