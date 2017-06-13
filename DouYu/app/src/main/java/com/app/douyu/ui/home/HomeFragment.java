package com.app.douyu.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.base.BaseFragmentPagerAdapter;
import com.app.douyu.bean.Config;
import com.app.douyu.bean.home.HomeTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeCateListView {
    @Bind(R.id.tabs) TabLayout mTabs;
    @Bind(R.id.viewpager) ViewPager mViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

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
        showContentView();

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


}
