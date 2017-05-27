package com.app.douyu.bean.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.app.douyu.R;
import com.app.douyu.base.App;
import com.app.douyu.ui.PcLiveVideoActivity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/26
 */
public class HomeCateItemAdapter extends BaseQuickAdapter<RoomListEntity, BaseViewHolder> {
    public HomeCateItemAdapter(int layoutResId, List<RoomListEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomListEntity item) {
        helper.setText(R.id.tv_count, getOnline(item.online));
        helper.setText(R.id.tv_name, item.nickname);
        helper.setText(R.id.tv_room_name, item.room_name);
        ImageView imageView = helper.getView(R.id.iv_room);
        Glide.with(mContext).load(item.room_src).into(imageView);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(App.getContext().getCurrentActivity(), PcLiveVideoActivity.class));
            }
        });
    }

    public static String getOnline(int online) {
        if (online < 10000) {
            return online + "";
        } else {
            BigDecimal b = new BigDecimal(((double) online) / 10000);
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return f1 + "万";
        }
    }

}
