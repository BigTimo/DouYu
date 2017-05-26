package com.app.douyu.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.bean.home.HomeRecommendHotCate;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomeCateFragment extends BaseFragment<HomePresenter> implements HomeContract.HomeCateView {

    @Bind(R.id.recycle_view) RecyclerView mRecycleView;
    private List<HomeRecommendHotCate> data = new ArrayList<>();
    private HomeCateAdapter mHomeCateAdapter;


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
        mHomeCateAdapter = new HomeCateAdapter(R.layout.item_home_cate, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mHomeCateAdapter);
//        mRecycleView.addItemDecoration(new );

    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void loadData() {
        mPresenter.getHomeCate(getArguments().getString("identification"));
    }

    @Override
    public void showHomeCateDetail(List<HomeRecommendHotCate> result) {
        showContentView();
        data.clear();
        data.addAll(result);
        mHomeCateAdapter.notifyDataSetChanged();
    }

}
