package com.app.douyu.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Free
 * @version 1.0
 * @since 2017/3/17
 */
public  class BasePresenter<V extends IBaseView> {
    protected V mView;
    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void setView(V v) {
        this.mView = v;
        this.onAttached();
    }

    public <V extends IBaseView> V getView(){
        return  (V)mView;
    }

    public  void onAttached(){}

    public void addSubscription(Subscription s) {
        mCompositeSubscription.add(s);
    }

    public void unSubscribe() {
        mCompositeSubscription.unsubscribe();
    }
}
