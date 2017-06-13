package com.app.douyu.net;

import com.app.douyu.bean.BaseResult;
import com.app.douyu.bean.home.HomeBanner;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.bean.home.HomeTitle;
import com.app.douyu.bean.home.RoomListEntity;
import com.app.douyu.bean.live.LiveTitle;
import com.app.douyu.bean.live.LiveTitleTwo;
import com.app.douyu.net.api.DouYuService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Free
 * @version 1.0
 * @since 2017/2/23
 */
public class HttpManager {


    public static DouYuService getDouYuService() {
        return HttpConfig.getInstance().getDouYuService();
    }


    /**
     * 统一的请求结果处理
     */
    private static <T> Subscription handleResult(Observable<BaseResult<T>> observable, final BaseCallBack<T> subscriber) {
        return observable
                .map(new Func1<BaseResult<T>, T>() {
                    @Override
                    public T call(BaseResult<T> result) {
                        if (result.error != 0) {
                            subscriber.onError(new ApiException(result));
                        }
                        //                        Timber.d("接收到数据===================> %s",new Gson().toJson(result.data));
                        return result.data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public static Subscription getHomeCateList(BaseCallBack<List<HomeTitle>> subscriber) {
        return handleResult(getDouYuService().getHomeCateList(), subscriber);
    }

    public static Subscription getHomeCate(String identification, BaseCallBack<List<HomeRecommendHotCate>> subscriber) {
        return handleResult(getDouYuService().getHomeCate(identification), subscriber);
    }

    public static Subscription getHomeBanner( BaseCallBack<List<HomeBanner>> subscriber) {
        return handleResult(getDouYuService().getHomeBanner(), subscriber);
    }


    public static Subscription getHomeHotColumn(BaseCallBack<List<RoomListEntity>> subscriber) {
        return handleResult(getDouYuService().getHomeHotColumn(), subscriber);
    }

    public static Subscription getHomeFaceScoreColumn(BaseCallBack<List<RoomListEntity>> subscriber) {
        return handleResult(getDouYuService().getHomeFaceScoreColumn(), subscriber);
    }

    public static Subscription getHomeRecommendHotCate(BaseCallBack<List<HomeRecommendHotCate>> subscriber) {
        return handleResult(getDouYuService().getHomeRecommendHotCate(), subscriber);
    }

    public static Subscription getHomeRecommend(final BaseCallBack<List<HomeRecommendHotCate>> subscriber) {
        Observable<List<HomeRecommendHotCate>> hot = HttpManager.getDouYuService().getHomeHotColumn().map(
                new Func1<BaseResult<List<RoomListEntity>>, List<HomeRecommendHotCate>>() {
                    @Override
                    public List<HomeRecommendHotCate> call(BaseResult<List<RoomListEntity>> result) {
                        List<HomeRecommendHotCate> homeCates = new ArrayList<>();
                        if (result.error == 0) {
                            HomeRecommendHotCate homeCate = new HomeRecommendHotCate();
                            homeCate.tag_name = "最热";
                            homeCate.room_list = result.data;
                        } else {
                            subscriber.onError(new ApiException(result));
                        }
                        return homeCates;
                    }

                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<List<HomeRecommendHotCate>> face = HttpManager.getDouYuService().getHomeFaceScoreColumn().map(
                new Func1<BaseResult<List<RoomListEntity>>, List<HomeRecommendHotCate>>() {
                    @Override
                    public List<HomeRecommendHotCate> call(BaseResult<List<RoomListEntity>> result) {
                        List<HomeRecommendHotCate> homeCates = new ArrayList<>();
                        if (result.error == 0) {
                            HomeRecommendHotCate homeCate = new HomeRecommendHotCate();
                            homeCate.tag_name = "颜值";
                            homeCate.room_list = result.data;
                        } else {
                            subscriber.onError(new ApiException(result));
                        }
                        return homeCates;
                    }

                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<List<HomeRecommendHotCate>> recommend = HttpManager.getDouYuService().getHomeRecommendHotCate().map(
                new Func1<BaseResult<List<HomeRecommendHotCate>>, List<HomeRecommendHotCate>>() {
                    @Override
                    public List<HomeRecommendHotCate> call(BaseResult<List<HomeRecommendHotCate>> result) {
                        List<HomeRecommendHotCate> homeCates = new ArrayList<>();
                        if (result.error == 0) {
                            homeCates.addAll(result.data);
                        } else {
                            subscriber.onError(new ApiException(result));
                        }
                        return homeCates;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        return Observable.concat(hot, face, recommend)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


    public static Subscription getLiveTitles(BaseCallBack<List<LiveTitle>> subscriber) {
        return handleResult(getDouYuService().getLiveTitles(), subscriber);
    }

    public static Subscription getLiveTitlesTwo(String shortName, BaseCallBack<List<LiveTitleTwo>> subscriber) {
        return handleResult(getDouYuService().getLiveTitlesTwo(shortName), subscriber);
    }

    public static Subscription getLiveDetail(String tag_id, BaseCallBack<List<RoomListEntity>> subscriber) {
        return handleResult(getDouYuService().getLiveDetail(tag_id), subscriber);
    }

    public static Subscription getLiveAll(BaseCallBack<List<RoomListEntity>> subscriber) {
        return handleResult(getDouYuService().getLiveAllList(), subscriber);
    }

    public static Subscription getLivSports(BaseCallBack<List<RoomListEntity>> subscriber) {
        return handleResult(getDouYuService().getLiveSportsAllList(), subscriber);
    }


}
