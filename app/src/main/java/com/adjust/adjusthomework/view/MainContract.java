package com.adjust.adjusthomework.view;

import android.content.SharedPreferences;

import com.adjust.adjusthomework.base.BaseContract;
import com.adjust.adjusthomework.data.model.SecondResponse;

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void showResult(SecondResponse second);
        void fadeProgressBar();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void sendSelectedSecond(Integer second);
        void sendSecondToServer(Integer... seconds);
        boolean secondIsValidForSending(int second);
    }

}
