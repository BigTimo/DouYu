package com.app.douyu.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.base.BaseFragmentPagerAdapter;
import com.app.douyu.bean.Config;
import com.app.douyu.bean.home.HomeTitle;
import com.app.mylibrary.view.MultipleStatusView;
import com.app.mylibrary.view.TextImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeCateListView {


    @Bind(R.id.tv_search) TextImageView mTvSearch;
    @Bind(R.id.iv_scanner) ImageView mIvScanner;
    @Bind(R.id.iv_history) ImageView mIvHistory;
    @Bind(R.id.multipleStatusView) MultipleStatusView mMultipleStatusView;
    TabLayout mTabs;
    ViewPager mViewpager;

    @Override
    public boolean setUseLayoutIdCustom() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mMultipleStatusView.setContentView(R.layout.layout_tab_pager);
        View contentView = mMultipleStatusView.getContentView();
        mTabs = (TabLayout) contentView.findViewById(R.id.tabs);
        mViewpager = (ViewPager) contentView.findViewById(R.id.viewpager);
    }

    @Override
    public MultipleStatusView getMultipleView() {
        return mMultipleStatusView;
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void loadData() {
        mPresenter.getHomeCateList();
    }

    @Override
    public void showHomeCateTitle(List<HomeTitle> result) {

        if (result == null) {
            return;
        }

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        titles.add("推荐");
        fragments.add(HomeCateFragment.getHomeCateFragment(Config.HOME_RECOMMEND));
        for (int i = 0; i < result.size(); i++) {
            titles.add(result.get(i).title);
            fragments.add(HomeCateFragment.getHomeCateFragment(result.get(i).identification));
        }
        BaseFragmentPagerAdapter baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments, titles);
        mViewpager.setAdapter(baseFragmentPagerAdapter);
        //设置Fragment的缓存个数 以当前页为中心，左右各4个
        mViewpager.setOffscreenPageLimit(4);
        mTabs.setTabMode(TabLayout.MODE_FIXED);
        mTabs.setupWithViewPager(mViewpager);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
