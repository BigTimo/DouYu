package com.app.douyu.ui.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.douyu.R;
import com.app.douyu.bean.home.HomeCateItemAdapter;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.bean.home.RoomListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomeCateAdapter extends BaseQuickAdapter<HomeRecommendHotCate, BaseViewHolder> {

    public HomeCateAdapter(int layoutResId, List<HomeRecommendHotCate> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeRecommendHotCate item) {
        helper.setText(R.id.tv_title, item.tag_name);

        //取前四个
        List<RoomListEntity> room_list = new ArrayList<>();
        if (item.room_list.size() > 4) {
            for (int i = 0; i < 4; i++) {
                room_list.add(item.room_list.get(i));
            }
        } else {
            room_list.clear();
            room_list.addAll(item.room_list);
        }

        HomeCateItemAdapter homeCateItemAdapter = new HomeCateItemAdapter(R.layout.item_home_cate_item, room_list);
        RecyclerView rv = helper.getView(R.id.recycle_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setAutoMeasureEnabled(true);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(homeCateItemAdapter);
    }
}
