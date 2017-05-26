package com.app.douyu.base;

import android.os.Bundle;

import com.app.douyu.view.MultipleStatusView;


/**
 * @author Free
 * @version 1.0
 * @since 2017/3/17
 */
public interface IBaseView extends MultipleStatusView.OnRetryClickListener {

    /**
     * 配置布局资源
     */
    int getLayoutId();

    /**
     * 初始化view,Activity的onCreate和Fragment的onCreateView中调用
     */
    void initView(Bundle savedInstanceState);


    /**
     * 加载数据
     */
    void loadData();

    /**
     * 可见时加载数据
     */
    void reLoadData();


    void showContentView();

    void showMultipleView(MultipleStatusView.ViewStatus status);

    void showMultipleView(MultipleStatusView.ViewStatus status, int layoutRes);
    void stopRefresh();

    MultipleStatusView getMultipleView();


}
