package com.app.douyu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.douyu.R;
import com.app.mylibrary.view.MultipleStatusView;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * @author Free
 * @version 1.0
 * @since 2017/1/5
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    private View mRootView;
    protected MultipleStatusView mMultipleStatusView;
    private SwipeRefreshLayout mMRefreshLayout;

    private boolean useLazyLoad = true;
    private boolean hadLoad = false;
    private boolean hadInit = false;
    private boolean isVisibleToUser = false;
    private boolean isInViewPager = false;


    //定义Presenter
    protected P mPresenter;

    @Override
    public boolean setUseLayoutIdCustom() {
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.v("onCreateView");
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
            return mRootView;
        }

        if (setUseLayoutIdCustom()) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            mRootView = inflater.inflate(R.layout.layout_base_fragment, container, false);
            mMRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.refreshLayout);
            mMultipleStatusView = (MultipleStatusView) mRootView.findViewById(R.id.multipleStatusView);
            mMRefreshLayout.setEnabled(useRefreshLayout(mMRefreshLayout));
            mMultipleStatusView.setContentView(getLayoutId());
            mMultipleStatusView.setOnRetryClickListener(this);
        }

        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.v("onActivityCreated");
        hadInit = true;
        initView(savedInstanceState);
        useLazyLoad = useLazyLoad();
        if (isInViewPager) {
            if (useLazyLoad) {
                setUserVisibleHint(isVisibleToUser);//初始化完成之后显示,解决第一个fragment不加载
            } else {
                loadData();
            }
        } else {
            loadData();
        }
    }


    protected abstract P getPresenter();

    /**
     * 默认不可下拉刷新
     *
     * @param refreshLayout
     * @return
     */
    protected boolean useRefreshLayout(SwipeRefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public MultipleStatusView getMultipleView() {
        return mMultipleStatusView;
    }

    /**
     * 在ViewPager中切换时，是否使用懒加载<br/>
     * 如果使用懒加载 ，在setUserVisibleHint中  -->初始化完成，没有加载过，可见，loadData
     * 如果不使用懒加载，在onActivityCreated中loadData
     */
    protected boolean useLazyLoad() {
        return true;
    }


    /**
     * 加载一次数据<br/>
     * 1.普通Fragment中onActivityCreated加载<br/>
     * 2.在ViewPager中切换时，如果使用懒加载 ，在setUserVisibleHint中  -->初始化完成，没有加载过，可见，loadData
     * 如果不使用懒加载，在onActivityCreated中loadData
     */
    @Override
    public void loadData() {
        Timber.v("loadData isInViewPager = %s", isInViewPager);
    }

    /**
     * 可见即加载数据<BR/>
     * 1.在TabHost中切换时，已创建的Fragment会隐藏，而再次显示时，不会调用onResume,
     * 在onResume中第一次loadData,在onHiddenChanged中再次loadData<BR/>
     * 2.配合使用ViewPager时，可见创建时会调用onResume,在切换可见时不会调用onResume只会调用setUserVisibleHint，
     * 并且setUserVisibleHint先于onAttach执行，<BR/>
     * 在onResume中第一次loadData，在ViewPager中切换可见时，如果初始化完成，在setUserVisibleHint中再次loadData.
     */
    @Override
    public void reLoadData() {
        Timber.v("reLoadData isInViewPager = %s", isInViewPager);
    }

    protected void startRefresh() {
        mMRefreshLayout.setRefreshing(true);
    }


    @Override
    public void onRetryClick(MultipleStatusView.ViewStatus viewStatus, View view) {
        loadData();
        reLoadData();
    }


    //------------------------------------Fragment的一些生命周期------------------------------------


    @Override
    public void onResume() {
        super.onResume();
        Timber.v("onResume");
        reLoadData();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isInViewPager = true;
        this.isVisibleToUser = isVisibleToUser;
        Timber.v("setUserVisibleHint Visible = %s", isVisibleToUser);
        if (hadInit && isVisibleToUser) {
            if (useLazyLoad && !hadLoad) {
                hadLoad = true;
                loadData();
            }
            reLoadData();
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        Timber.v("onHiddenChanged hidden = %s", hidden);
        super.onHiddenChanged(hidden);
        if (!hidden) {
            reLoadData();
        }
    }

    @Override
    public void onDestroyView() {
        Timber.v("onDestroyView");
        hadLoad = false;
        ButterKnife.unbind(this);
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        super.onDestroyView();
    }
}
