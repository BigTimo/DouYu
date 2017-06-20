package com.app.douyu.net;


import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.app.douyu.R;
import com.app.douyu.base.IBaseView;
import com.app.douyu.bean.BaseResult;
import com.app.mylibrary.view.MultipleStatusView;

import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * @author Free
 * @version 1.0
 * @since 2017/2/23
 */
public abstract class BaseCallBack<T> extends Subscriber<T> {
    private boolean showLoading = true;
    private boolean showTip = true;
    private MultipleStatusView mMultipleView;
    private AnimationDrawable mDrawable;

    public BaseCallBack(IBaseView view) {
        this(view, true);
    }

    public BaseCallBack(IBaseView view, boolean showTip) {
        this.showTip = showTip;
        if (view != null) {
            mMultipleView = view.getMultipleView();
        }
    }

    public BaseCallBack(boolean showLoading, boolean showTip) {
        this.showLoading = showLoading;
        this.showTip = showTip;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mMultipleView != null) {
            if (mMultipleView.getViewStatus() != MultipleStatusView.ViewStatus.CONTENT) {
                mMultipleView.showMultipleStatus(MultipleStatusView.ViewStatus.LOADING);
                View loadingView = mMultipleView.getLoadingView();
                ImageView imageView = (ImageView) loadingView.findViewById(R.id.iv);
                mDrawable = (AnimationDrawable) imageView.getDrawable();
                mDrawable.start();
            }
        }
    }

    @Override
    public void onCompleted() {
        if (mMultipleView != null) {
            mMultipleView.showContentView();
        }
        if (mDrawable != null) {
            mDrawable.stop();
        }

    }

    @Override
    public void onError(Throwable e) {
        if (mDrawable != null) {
            mDrawable.stop();
        }
        //加载框消失
        if (e instanceof ApiException) {
            //服务器返回的错误的数据，与预期不符
            ApiException exception = (ApiException) e;
            if (!error(exception.getBaseResult())) {
                if (showTip) {
                    //提示错误信息
                    //                    DialogUtil.showTip(exception.getMessage());
                }
            }
        } else {
            fail(e);
        }
    }


    @Override
    public void onNext(T t) {
        onCompleted();
        response(t);
    }

    /**
     * 请求结果正确
     */
    protected abstract void response(T result);


    /**
     * 错误的服务器返回数据，与预期不符
     * 默认提示错误信息
     * 如需自己处理，重写此方法，返回true，自己处理一切
     */
    protected boolean error(BaseResult result) {
        return false;
    }


    /**
     * 请求失败，包括网络链接错误，超时
     *
     * @param e
     */
    protected void fail(Throwable e) {

        if (mMultipleView != null && mMultipleView.getViewStatus() != MultipleStatusView.ViewStatus.CONTENT) {
            mMultipleView.showMultipleStatus(MultipleStatusView.ViewStatus.NO_NETWORK);
        }

        if (showTip) {
            if (e instanceof TimeoutException) {
                //提示连接超时信息
                //                DialogUtil.showTip(R.string.tip_net_time_out);
            } else {
                //提示网络链接错误信息
                //                DialogUtil.showTip(R.string.tip_net_error);
            }
        }
    }


}
