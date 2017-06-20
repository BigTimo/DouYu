package com.app.douyu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.douyu.R;
import com.app.douyu.view.ToolBarView;
import com.app.mylibrary.view.MultipleStatusView;

import butterknife.ButterKnife;


/**
 * @author Free
 * @version 1.0
 * @since 2017/1/5
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView, MultipleStatusView.OnRetryClickListener {

    protected ToolBarView mToolBar;
    protected MultipleStatusView mMultipleStatusView;


    //定义Presenter
    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayoutRes();
        ButterKnife.bind(this);
        //        TRouter.bind(this);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
        initView(savedInstanceState);
        loadData();
    }


    @Override
    protected void onResume() {
        super.onResume();
        reLoadData();
    }

    private void initLayoutRes() {
        if (setUseLayoutIdCustom()) {
            setContentView(getLayoutId());
        } else {
            setContentView(R.layout.layout_base_activity);
            mMultipleStatusView = (MultipleStatusView) findViewById(R.id.multipleStatusView);
            mToolBar = (ToolBarView) findViewById(R.id.toolBar);
            mToolBar.setFocusableInTouchMode(true);
            mMultipleStatusView.setContentView(getLayoutId());
            mMultipleStatusView.setOnRetryClickListener(this);
        }

    }

    public abstract P getPresenter();



    @Override
    public boolean setUseLayoutIdCustom() {
        return false;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void reLoadData() {
    }


    @Override
    public MultipleStatusView getMultipleView() {
        return mMultipleStatusView;
    }

    @Override
    public void onRetryClick(MultipleStatusView.ViewStatus viewStatus, View view) {
        loadData();
        reLoadData();
    }


    @Override
    public void onDestroy() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showMultipleStatus(MultipleStatusView.ViewStatus.EMPTY);
        }
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        super.onDestroy();
    }


}
