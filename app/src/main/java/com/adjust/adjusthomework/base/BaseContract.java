package com.adjust.adjusthomework.base;

public interface BaseContract {

    interface BasePresenter<V> {
        void detach();
    }

    /**
     * SDK Base View Contract
     */
    interface BaseView {
        /**
         * show or hide Loading
         *
         * @param show for showing pass true else pass false
         */
        void showLoading(Boolean show);

        /**
         * show Error to user
         */
        void showError(int message);

        void showError(String message);

        /**
         * show Message To user
         */
        void showMessage(int message);

        void showMessage(String message);


    }
}
