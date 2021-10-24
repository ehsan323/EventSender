package com.adjust.adjusthomework.view;

import android.content.SharedPreferences;

import com.adjust.adjusthomework.base.BasePresenter;
import com.adjust.adjusthomework.data.model.SecondResponse;
import com.adjust.adjusthomework.data.network.SecondCallback;
import com.adjust.adjusthomework.data.repository.AdjustSecondRepository;

import java.util.HashSet;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.View mView;
    private final HashSet<String> sentSeconds = new HashSet<>();
    private final AdjustSecondRepository repository = new AdjustSecondRepository();


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

    /**
     * check and send event.
     *
     * @param second value
     */
    @Override
    public void sendSelectedSecond(Integer second) {
        if (!secondIsValidForSending(second)) {
            sendSecondToServer(second);
        } else {
            showError("The Second has been sent before to server!");
        }

    }

    /**
     * send values to server
     *
     * @param seconds one or more events.
     */
    @Override
    public void sendSecondToServer(Integer... seconds) {
        repository.sendSecondEvent(seconds, new SecondCallback() {
            @Override
            public void processFinish(SecondResponse output, int value) {
                mView.showResult(output);
                sentSeconds.add(String.valueOf(value));
            }

            @Override
            public void processFailed(String error) {
                mView.showError(error);
            }

        });
    }


    /**
     * Check the value has been sent or not.
     *
     * @param second value
     * @return true or false.
     */
    @Override
    public boolean secondIsValidForSending(int second) {
        return sentSeconds.contains(String.valueOf(second));
    }



}
