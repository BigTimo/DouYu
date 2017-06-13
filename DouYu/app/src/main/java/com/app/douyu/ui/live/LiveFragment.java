package com.app.douyu.ui.live;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.base.BaseFragmentPagerAdapter;
import com.app.douyu.bean.Config;
import com.app.douyu.bean.live.LiveTitle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author Free
 * @version 1.0
 * @since 2017/6/12
 */
public class LiveFragment extends BaseFragment<LivePresenter> implements LiveContract.TitleView {
    @Bind(R.id.tabs) TabLayout mTabs;
    @Bind(R.id.viewpager) ViewPager mViewpager;

    @Override
    public int getLayoutId() {
        return R.layout.layout_tab_pager;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected LivePresenter getPresenter() {
        return new LivePresenter();
    }

    @Override
    public void loadData() {
        mPresenter.getLiveTitles();
    }

    @Override
    public void showTitles(List<LiveTitle> result) {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        titles.add("常用");
        fragments.add(LiveDetailFragment.getInstance(Config.LIVE_ALL));
        titles.add("全部");
        fragments.add(LiveDetailFragment.getInstance(Config.LIVE_ALL));
        for (int i = 0; i < result.size(); i++) {
            titles.add(result.get(i).cate_name);
            fragments.add(LiveDetailFragment.getInstance(result.get(i).short_name));
        }
        titles.add("体育");//企鹅
        fragments.add(LiveDetailFragment.getInstance(Config.LIVE_SPORTS));
        BaseFragmentPagerAdapter baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments, titles);
        mViewpager.setAdapter(baseFragmentPagerAdapter);
        //设置Fragment的缓存个数 以当前页为中心，左右各4个
        mTabs.setupWithViewPager(mViewpager);
    }
}
