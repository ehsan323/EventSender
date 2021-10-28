package com.adjust.adjusthomework.view;

import android.content.SharedPreferences;

import com.adjust.adjusthomework.base.BasePresenter;
import com.adjust.adjusthomework.data.model.SecondResponse;
import com.adjust.adjusthomework.data.network.SecondCallback;
import com.adjust.adjusthomework.data.repository.AdjustSecondRepository;
import com.adjust.adjusthomework.util.AdjustUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public static final String QUEUED_VALUES = "queuedValues";
    private MainContract.View mView;
    public final HashSet<String> sentSeconds = new HashSet<>();
    public final AdjustSecondRepository repository = new AdjustSecondRepository();


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
                sentSeconds.add(String.valueOf(value));
                mView.showResult(output);
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


    /**
     * Cache Queue values
     *
     * @param sharedPreferences SharedPreferences
     */
    @Override
    public void cacheQueueValues(SharedPreferences sharedPreferences) {
        HashSet<Integer> list = new HashSet<>(repository.queue);
        sharedPreferences.edit().putString(QUEUED_VALUES, AdjustUtil.covertIntegerListToString(list)).apply();
    }


    /**
     * load cached values by SharedPreferences
     *
     * @param sharedPreferences SharedPreferences
     */
    @Override
    public void loadAndHandleCachedQueues(SharedPreferences sharedPreferences) {
        HashSet<Integer> values = AdjustUtil.convertStringToIntegerList(sharedPreferences.getString(QUEUED_VALUES, ""));
        if (values.size() > 0) {
            sendSecondToServer(AdjustUtil.convertIntegers(values));
        } else {
            mView.fadeProgressBar();
        }

    }


}
