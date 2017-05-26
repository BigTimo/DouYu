package com.app.douyu.net.api;


import com.app.douyu.bean.BaseResult;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.bean.home.HomeTitle;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Free
 * @version 1.0
 * @since 2017/2/23
 */
public interface DouYuService {

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


}
