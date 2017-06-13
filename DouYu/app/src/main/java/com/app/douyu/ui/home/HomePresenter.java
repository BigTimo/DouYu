package com.app.douyu.ui.home;


import android.text.TextUtils;

import com.app.douyu.bean.Config;
import com.app.douyu.bean.home.HomeBanner;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.bean.home.HomeTitle;
import com.app.douyu.net.BaseCallBack;
import com.app.douyu.net.HttpManager;

import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomePresenter extends HomeContract.Presenter {


    @Override
    public void getHomeCateList() {
        addSubscription(HttpManager.getHomeCateList(new BaseCallBack<List<HomeTitle>>() {
            @Override
            protected void response(List<HomeTitle> result) {
                ((HomeContract.HomeCateListView) mView).showHomeCateTitle(result);
            }
        }));
    }

    @Override
    public void getHomeBanner() {
        addSubscription(HttpManager.getHomeBanner(new BaseCallBack<List<HomeBanner>>() {
            @Override
            protected void response(List<HomeBanner> result) {
                ((HomeContract.HomeCateView) mView).showHomeBanner(result);
            }
        }));
    }


    @Override
    public void getHomeCate(String identification) {
        if (TextUtils.equals(identification, Config.HOME_RECOMMEND)) {
            addSubscription(HttpManager.getHomeRecommend(new BaseCallBack<List<HomeRecommendHotCate>>() {
                @Override
                protected void response(List<HomeRecommendHotCate> result) {
                    ((HomeContract.HomeCateView) mView).showHomeCateDetail(result);
                }
            }));
        } else {
            addSubscription(HttpManager.getHomeCate(identification, new BaseCallBack<List<HomeRecommendHotCate>>() {
                @Override
                protected void response(List<HomeRecommendHotCate> result) {
                    ((HomeContract.HomeCateView) mView).showHomeCateDetail(result);
                }
            }));

        }
    }

}
