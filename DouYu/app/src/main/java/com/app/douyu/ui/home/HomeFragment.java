package com.app.douyu.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.EditText;

import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.base.BaseFragmentPagerAdapter;
import com.app.douyu.bean.home.HomeTitle;

import java.util.List;

import butterknife.Bind;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeCateListView {
    @Bind(R.id.et_search) EditText mEtSearch;
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
        String[] titles = new String[result.size()];
        Fragment[] fragments = new Fragment[result.size()];
        for (int i = 0; i < result.size(); i++) {
            titles[i] = result.get(i).title;
            fragments[i] = HomeCateFragment.getHomeCateFragment(result.get(i).identification);
        }
        BaseFragmentPagerAdapter baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getFragmentManager(), fragments, titles);
        mViewpager.setAdapter(baseFragmentPagerAdapter);
        //设置Fragment的缓存个数 以当前页为中心，左右各3个
        mTabs.setupWithViewPager(mViewpager);
    }



}
