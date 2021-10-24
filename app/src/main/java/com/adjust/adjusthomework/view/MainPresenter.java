package com.adjust.adjusthomework.view;

import android.content.SharedPreferences;

import com.adjust.adjusthomework.base.BasePresenter;
import java.util.HashSet;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.View mView;
    private final HashSet<String> sentSeconds = new HashSet<>();


    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public MainContract.View getView() {
        return mView;
    }


    @Override
    public void sendSelectedSecond(Integer second) {

    }

    @Override
    public void sendSecondToServer(Integer... seconds) {

    }

    @Override
    public boolean secondIsValidForSending(int second) {
        return false;
    }

    @Override
    public void cacheQueueValues(SharedPreferences sharedPreferences) {

    }

    @Override
    public void loadAndHandleCachedQueues(SharedPreferences sharedPreferences) {

    }
}
