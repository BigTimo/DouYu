package com.app.douyu.net.api;


import com.app.douyu.bean.BaseResult;
import com.app.douyu.bean.home.HomeBanner;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.bean.home.HomeTitle;
import com.app.douyu.bean.home.RoomListEntity;
import com.app.douyu.bean.live.LiveTitle;
import com.app.douyu.bean.live.LiveTitleTwo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Free
 * @version 1.0
 * @since 2017/2/23
 */
public interface DouYuService {

    //------------------------------------首页------------------------------------

    /**
     * 首页分类列表
     */
    @GET(ApiConfig.getHomeCateList)
    Observable<BaseResult<List<HomeTitle>>> getHomeCateList();


    /**
     * 首页 列表详情页
     */
    @GET(ApiConfig.getHomeCate)
    Observable<BaseResult<List<HomeRecommendHotCate>>> getHomeCate(
            @Query("identification") String identification
    );

    /**
     * 推荐-轮播图
     */
    @GET(ApiConfig.getHomeBanner)
    Observable<BaseResult<List<HomeBanner>>> getHomeBanner();

    /**
     * 推荐最热
     */
    @GET(ApiConfig.getHomeHotColumn)
    Observable<BaseResult<List<RoomListEntity>>> getHomeHotColumn();

    /**
     * 推荐  颜值
     */
    @GET(ApiConfig.getHomeFaceScoreColumn)
    Observable<BaseResult<List<RoomListEntity>>> getHomeFaceScoreColumn();

    /**
     * 首页 推荐其他
     */
    @GET(ApiConfig.getHomeRecommendHotCate)
    Observable<BaseResult<List<HomeRecommendHotCate>>> getHomeRecommendHotCate();

    //------------------------------------直播------------------------------------

    /**
     * 直播标题
     */
    @GET(ApiConfig.getLiveTitles)
    Observable<BaseResult<List<LiveTitle>>> getLiveTitles();

    /**
     * 直播-二级标题
     */
    @GET(ApiConfig.getLiveTitlesTwo)
    Observable<BaseResult<List<LiveTitleTwo>>> getLiveTitlesTwo(
            @Query("shortName") String shortName

    );

    /**
     * 直播-详细列表
     */
    @GET(ApiConfig.getLiveDetail)
    Observable<BaseResult<List<RoomListEntity>>> getLiveDetail(
            @Path("tag_id") String tag_id

    );

    /**
     * 直播-全部
     */
    @GET(ApiConfig.getLiveAll)
    Observable<BaseResult<List<RoomListEntity>>> getLiveAllList();

    /**
     * 直播-体育
     */
    @GET(ApiConfig.getLivSports)
    Observable<BaseResult<List<RoomListEntity>>> getLiveSportsAllList();


    //------------------------------------播放------------------------------------


}
