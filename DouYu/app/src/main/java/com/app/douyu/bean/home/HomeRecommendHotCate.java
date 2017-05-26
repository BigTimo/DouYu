package com.app.douyu.bean.home;

import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class HomeRecommendHotCate {


    /**
     * room_list : []
     * tag_name : 最热
     * tag_id : 9
     * icon_url :
     * small_icon_url :
     * count : 508
     * count_ios : 416
     * push_vertical_screen : 0
     */

    public String tag_name;
    public String tag_id;
    public String icon_url;
    public String small_icon_url;
    public int count;
    public int count_ios;
    public int push_vertical_screen;
    public List<RoomListEntity> room_list;

}
