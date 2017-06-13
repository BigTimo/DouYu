package com.app.douyu.ui.live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andview.refreshview.XRefreshView;
import com.app.douyu.R;
import com.app.douyu.base.BaseFragment;
import com.app.douyu.bean.home.RoomListEntity;
import com.app.douyu.bean.live.LiveTitleTwo;
import com.app.douyu.ui.home.adapter.HomeCateItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author Free
 * @version 1.0
 * @since 2017/6/12
 */
public class LiveDetailFragment extends BaseFragment<LivePresenter> implements LiveContract.DetailView {


    @Bind(R.id.recycle_view) RecyclerView mRecycleView;
    @Bind(R.id.refreshview) XRefreshView mRefreshview;
    private List<RoomListEntity> mData = new ArrayList<>();
    private HomeCateItemAdapter mAdapter;

    public static Fragment getInstance(String shortName) {
        LiveDetailFragment fragment = new LiveDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("shortName", shortName);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (mRecycleView != null) {
            mRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            mAdapter = new HomeCateItemAdapter(R.layout.item_home_cate_item, mData);
            mRecycleView.setAdapter(mAdapter);
        }

    }

    @Override
    protected LivePresenter getPresenter() {
        return new LivePresenter();
    }

    @Override
    public void loadData() {
        mPresenter.getLiveTitlesTwo(getArguments().getString("shortName"));
    }

    @Override
    public void showTitlesTow(List<LiveTitleTwo> result) {
    }

    @Override
    public void showLiveDetail(List<RoomListEntity> result) {
        mData.clear();
        mData.addAll(result);
        mAdapter.notifyDataSetChanged();
    }

}
