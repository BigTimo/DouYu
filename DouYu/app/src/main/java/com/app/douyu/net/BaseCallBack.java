package com.app.douyu.net;


import com.app.douyu.base.IBaseView;
import com.app.douyu.bean.BaseResult;
import com.app.douyu.view.MultipleStatusView;

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
    private IBaseView mView;


    public BaseCallBack() {

    }

    public BaseCallBack(boolean showLoading, boolean showTip) {
        this(showLoading, showTip, null);
    }

    public BaseCallBack(boolean showLoading, boolean showTip, IBaseView view) {
        mView = view;
        this.showLoading = showLoading;
        this.showTip = showTip;
    }

    @Override
    public void onStart() {
        //加载框显示
        super.onStart();
    }

    @Override
    public void onCompleted() {
        //加载框消失

    }

    @Override
    public void onError(Throwable e) {
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
//        Timber.d("onNext========>%s", new Gson().toJson(t));
        onCompleted();
        stopRefresh();
        response(t);
        if (mView != null) {
            mView.showContentView();
        }
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
        stopRefresh();
        return false;
    }


    /**
     * 请求失败，包括网络链接错误，超时
     *
     * @param e
     */
    protected void fail(Throwable e) {
        stopRefresh();
        if (mView != null) {
            if (mView.getMultipleView().getViewStatus() != MultipleStatusView.ViewStatus.CONTENT) {
                mView.showMultipleView(MultipleStatusView.ViewStatus.NO_NETWORK);
            }
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

    private void stopRefresh() {
        if (mView != null) {
            mView.stopRefresh();
        }
    }

}
