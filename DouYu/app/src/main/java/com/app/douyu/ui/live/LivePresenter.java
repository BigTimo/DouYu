package com.app.douyu.ui.live;

import android.text.TextUtils;

import com.app.douyu.bean.Config;
import com.app.douyu.bean.home.RoomListEntity;
import com.app.douyu.bean.live.LiveTitle;
import com.app.douyu.bean.live.LiveTitleTwo;
import com.app.douyu.net.BaseCallBack;
import com.app.douyu.net.HttpManager;

import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/6/11
 */
public class LivePresenter extends LiveContract.Presenter {

    @Override
    public void getLiveTitles() {
        HttpManager.getLiveTitles(new BaseCallBack<List<LiveTitle>>() {
            @Override
            protected void response(List<LiveTitle> result) {
                ((LiveContract.TitleView) mView).showTitles(result);
            }
        });
    }

    @Override
    public void getLiveTitlesTwo(String shortName) {
        if (TextUtils.equals(shortName, Config.LIVE_ALL)) {
            getLiveAll();
        } else if (TextUtils.equals(shortName, Config.LIVE_SPORTS)) {
            getLivSports();
        } else {
            addSubscription(HttpManager.getLiveTitlesTwo(shortName, new BaseCallBack<List<LiveTitleTwo>>() {
                @Override
                protected void response(List<LiveTitleTwo> result) {
                    ((LiveContract.DetailView) mView).showTitlesTow(result);
                    getLiveDetail(result.get(0).tag_id);
                }
            }));
        }
    }

    @Override
    public void getLiveDetail(String tag_id) {
        addSubscription(HttpManager.getLiveDetail(tag_id, new BaseCallBack<List<RoomListEntity>>() {
            @Override
            protected void response(List<RoomListEntity> result) {
                ((LiveContract.DetailView) mView).showLiveDetail(result);
            }

        }));
    }

    @Override
    public void getLiveAll() {
        addSubscription(HttpManager.getLiveAll(new BaseCallBack<List<RoomListEntity>>() {
            @Override
            protected void response(List<RoomListEntity> result) {
                ((LiveContract.DetailView) mView).showLiveDetail(result);
            }
        }));

    }

    @Override
    public void getLivSports() {
        addSubscription(HttpManager.getLivSports(new BaseCallBack<List<RoomListEntity>>() {
            @Override
            protected void response(List<RoomListEntity> result) {
                ((LiveContract.DetailView) mView).showLiveDetail(result);
            }
        }));
    }
}
