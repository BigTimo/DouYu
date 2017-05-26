package com.app.douyu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.app.douyu.base.App;
import com.app.douyu.base.BaseActivity;
import com.app.douyu.base.BasePresenter;
import com.app.douyu.ui.home.HomeFragment;
import com.app.douyu.view.FragmentTabHost;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    long lastClickTime  = 0L;

    @Bind(R.id.tabhost) FragmentTabHost mTabhost;

    private int[] images = {R.drawable.selector_home, R.drawable.selector_live, R.drawable.selector_follow, R.drawable.selector_find, R.drawable.selector_user};
    private String[] titles = {"首页", "直播", "关注", "发现", "我的"};
    //    private final Class[] fragments = {HomeFragment.class, LiveFragment.class, FollowFragment.class, FindFragment.class, UserFragment.class};
    private final Class[] fragments = {HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class, HomeFragment.class};

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
        mTabhost.setup(this, getSupportFragmentManager(), R.id.main_content);//设置替换哪个布局
        for (int i = 0; i < fragments.length; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(titles[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabhost.addTab(tabSpec, fragments[i], null);
            mTabhost.getTabWidget().setDividerDrawable(null);//设置每个TabView的控件
        }
    }

    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tabhost, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tabhost_item);
        imageView.setImageResource(images[index]);
        TextView textView = (TextView) view.findViewById(R.id.tv_tabhost_item);
        textView.setText(titles[index]);
        return view;
    }


    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - lastClickTime > 2000) {
            lastClickTime = System.currentTimeMillis();
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_LONG).show();
        }


        App.getContext().finishAll();
    }
}
