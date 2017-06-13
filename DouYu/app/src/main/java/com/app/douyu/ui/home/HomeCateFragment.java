package com.app.douyu.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.bean.Config;
import com.app.douyu.bean.home.HomeBanner;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.ui.home.adapter.HomeCateAdapter;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomeCateFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeCateView {

    @Bind(R.id.refreshview) XRefreshView mRefreshview;
    @Bind(R.id.banner) Banner mBanner;
    @Bind(R.id.recycle_view) RecyclerView mRecycleView;
    private List<HomeRecommendHotCate> data = new ArrayList<>();
    private HomeCateAdapter mHomeCateAdapter;
    private String mIdentification;


    public static HomeCateFragment getHomeCateFragment(String identification) {
        HomeCateFragment homeCateFragment = new HomeCateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("identification", identification);
        homeCateFragment.setArguments(bundle);
        return homeCateFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_recycle;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mIdentification = getArguments().getString("identification");

        mHomeCateAdapter = new HomeCateAdapter(R.layout.item_home_cate, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mHomeCateAdapter);

        if (TextUtils.equals(mIdentification, Config.HOME_RECOMMEND)) {
            mPresenter.getHomeBanner();
            mBanner.setVisibility(View.VISIBLE);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getContext()).load(path).into(imageView);
                }
            });
        } else {
            mBanner.setVisibility(View.GONE);
        }


        mRefreshview.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });


        //        mRecycleView.addItemDecoration(new );

    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void loadData() {
        if (TextUtils.equals(mIdentification, Config.HOME_RECOMMEND)) {
            mPresenter.getHomeBanner();
        }
        mPresenter.getHomeCate(mIdentification);
    }

    @Override
    public void showHomeBanner(List<HomeBanner> result) {
        List<String> images = new ArrayList<>();
        for (HomeBanner homeBanner : result) {
            images.add(homeBanner.tv_pic_url);
        }
        mBanner.setImages(images);
        mBanner.start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                // TODO: 2017/6/13 各种页面
            }
        });


    }

    @Override
    public void showHomeCateDetail(List<HomeRecommendHotCate> result) {
        mRefreshview.stopRefresh(true);
        showContentView();
        data.clear();
        data.addAll(result);
        mHomeCateAdapter.notifyDataSetChanged();
    }

}
