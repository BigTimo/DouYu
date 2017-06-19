package com.app.douyu;

import android.os.Bundle;
import android.widget.Toast;

import com.app.douyu.base.App;
import com.app.douyu.base.BaseActivity;
import com.app.douyu.base.BasePresenter;
import com.app.douyu.ui.home.HomeFragment;
import com.app.douyu.ui.home.UserFragment;
import com.app.douyu.ui.live.LiveFragment;
import com.app.douyu.view.NavigateTabBar;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    long lastClickTime = 0L;
    @Bind(R.id.tabhost) NavigateTabBar mNavigateTabBar;

    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_LIVE = "直播";
    private static final String TAG_PAGE_FOLLOW = "关注";
    private static final String TAG_PAGE_VIDEO = "发现";
    private static final String TAG_PAGE_USER = "我的";

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean setUseLayoutIdCustom() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);
        mNavigateTabBar.addTab(HomeFragment.class, new NavigateTabBar.TabParam(R.drawable.home_pressed, R.drawable.home_selected, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(LiveFragment.class, new NavigateTabBar.TabParam(R.drawable.live_pressed, R.drawable.live_selected, TAG_PAGE_LIVE));
        mNavigateTabBar.addTab(HomeFragment.class, new NavigateTabBar.TabParam(R.drawable.follow_pressed, R.drawable.follow_selected, TAG_PAGE_FOLLOW));
        mNavigateTabBar.addTab(UserFragment.class, new NavigateTabBar.TabParam(R.drawable.video, R.drawable.video_selected, TAG_PAGE_VIDEO));
        mNavigateTabBar.addTab(UserFragment.class, new NavigateTabBar.TabParam(R.drawable.user_pressed, R.drawable.user_selected, TAG_PAGE_USER));
        mNavigateTabBar.setTabSelectListener(new NavigateTabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(NavigateTabBar.ViewHolder holder) {
                //                Toast.makeText(MainActivity.this, "信息为:"+holder.tag, Toast.LENGTH_SHORT).show();
                switch (holder.tag.toString()) {
                    //                    首页
                    case TAG_PAGE_HOME:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    //                    直播
                    case TAG_PAGE_LIVE:

                        mNavigateTabBar.showFragment(holder);
                        break;
                    //                    关注
                    case TAG_PAGE_FOLLOW:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    //                    发现
                    case TAG_PAGE_VIDEO:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    //                    我的
                    case TAG_PAGE_USER:
                        if (mNavigateTabBar != null)
                            mNavigateTabBar.showFragment(holder);
                        break;
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastClickTime > 2000) {
            lastClickTime = System.currentTimeMillis();
            Toast.makeText(this, "再次点击退出程序哟~", Toast.LENGTH_LONG).show();
        } else {
            App.getContext().finishAll();
            System.exit(0);
        }
    }

}
