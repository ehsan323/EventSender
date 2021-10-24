package com.adjust.adjusthomework.base;

public abstract class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {

    public abstract V getView();

    public boolean isViewAttached() {
        return getView() != null;
    }

    public void showError(String error) {
        getView().showError(error);
    }

    public void showMessage(String message) {
        getView().showError(message);
    }
}
