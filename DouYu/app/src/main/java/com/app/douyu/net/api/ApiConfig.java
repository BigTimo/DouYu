package com.app.douyu.net.api;


/**
 * @author Free
 * @version 1.0
 * @since 2017/2/28
 */
public class ApiConfig {

    //    Base地址
    public static String BASE_url = "http://capi.douyucdn.cn";
    //     OldBase地址
    public static String oldBaseUrl = "http://coapi.douyucdn.cn";


    /**
     * ************************* 首页接口*******************************
     */
    //     首页标题列表
    public static final String getHomeCateList = "/api/homeCate/getCateList";
    //     首页---(手游娱乐趣玩)
    public static final String getHomeCate = "/api/homeCate/getHotRoom";

    //****************************推荐模块***************************************

    //    首页---推荐---轮播
    public static final String getHomeBanner = "/api/v1/slide/6";
    //    首页---推荐---热栏目
    public static final String getHomeHotColumn = "/api/v1/getbigDataRoom";
    //    首页---推荐---颜值栏目
    public static final String getHomeFaceScoreColumn = "/api/v1/getVerticalRoom";
    //    首页---推荐---种类
    public static final String getHomeRecommendHotCate = "/api/v1/getHotCate";
    //    栏目更多 --- 二级分类列表
    public static final String getHomeColumnMoreCate = "/api/v1/getThreeCate";
    //    栏目更多 --- 全部列表
    public static final String getHomeColumnMoreAllList = "/api/v1/live/";
    //    栏目更多----其他列表
    public static final String getHomeColumnMoreOtherList = "/api/v1/getThreeList";

    //****************************其他***************************************

    /**
     * ************************* 直播接口*******************************
     */
    //    直播其他栏目分类
    public static final String getLiveTitles = "/api/v1/getColumnList";
    //    全部直播
    public static final String getLiveAll = "/api/v1/live";
    //    其他二级栏目分类
    public static final String getLiveTitlesTwo = "/api/v1/getColumnDetail";
    //   二级栏目分类列表
    public static final String getLiveDetail = "/api/v1/live/{tag_id}";
    //    体育直播
    public static final String getLivSports = "/api/v1/qie";

    /**
     * *****************************视频接口***************************************
     */
    //    视频---推荐  http://apiv2.douyucdn.cn/video/Video/getHotVideoList1?clicknum=2&token=&client_sys=android
    public static final String getVideoHotColumn = "/video/Video/getHotVideoList1";

    //    视频---热门作者栏目  http://apiv2.douyucdn.cn/video/Home/getHotAuthors?client_sys=android
    public static final String getVideoHotAutherColumn = "/video/Home/getHotAuthors";

    //    其他热门 种类  http://apiv2.douyucdn.cn/video/Video/getCateHotVideoList1?token=&client_sys=android
    public static final String getVideoRecommendHotCate = "/video/Video/getCateHotVideoList1";

    //  视频---全部分类(一级分类)  http://apiv2.douyucdn.cn/video/Cate/getVideoHomeCate1?client_sys=android
    public static final String getVideoCateList = "/video/Cate/getVideoHomeCate1";

    //  视频---全部分类(二级分类)  http://apiv2.douyucdn.cn/video/Cate/getVideoCate2?cid1=3&client_sys=android
    public static final String getVideoReCateList = "/video/Cate/getVideoCate2";
    // 视频---视频列表 http://apiv2.douyucdn.cn/video/Videoroomlist/getRecVideoList?cid1=1&cid2=5&offset=0&limit=20&action=hot&client_sys=android
    public static final String getVideoOtherList = "/video/Videoroomlist/getRecVideoList";

    /**
     * ************************* 直播视频 *******************************
     */
    //    新接口
    public static final String getLiveVideo = "/api/v1/room/{room_id}";

    //    老接口
    public static final String getOldLiveVideo = "/lapi/live/thirdPart/getPlay/";
    /**
     * ********************************************************************
     */


    public static final String getPersonInfo = "/api/v1/login";

}
