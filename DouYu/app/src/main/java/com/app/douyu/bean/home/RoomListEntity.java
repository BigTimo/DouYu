package com.app.douyu.bean.home;

import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class RoomListEntity  {


    /**
     * room_id : 1949709
     * room_src : https://rpic.douyucdn.cn/a1705/25/14/1949709_170525143830.jpg
     * vertical_src : https://rpic.douyucdn.cn/a1705/25/14/1949709_170525143830.jpg
     * isVertical : 0
     * cate_id : 181
     * room_name : 帅气小露娜日常排位
     * show_status : 1
     * subject :
     * show_time : 1495690458
     * owner_uid : 24120550
     * nickname : 纤绾丶
     * online : 11635
     * game_name : 王者荣耀
     * child_id : 470
     * avatar_mid : https://apic.douyucdn.cn/upload/avanew/face/201703/31/13/c7091d2620186e1eb0fbc8911aa1e39a_middle.jpg
     * avatar_small : https://apic.douyucdn.cn/upload/avanew/face/201703/31/13/c7091d2620186e1eb0fbc8911aa1e39a_small.jpg
     * jumpUrl :
     * icon_data : {"status":5,"icon_url":"","icon_width":0,"icon_height":0}
     * anchor_city :
     */

    public String room_id;
    public String room_src;
    public String vertical_src;
    public int isVertical;
    public String cate_id;
    public String room_name;
    public String show_status;
    public String subject;
    public String show_time;
    public String owner_uid;
    public String nickname;
    public int online;
    public String game_name;
    public int child_id;
    public String avatar_mid;
    public String avatar_small;
    public String jumpUrl;
    public IconDataBean icon_data;
    public String anchor_city;
    /**
     * cate_id : 26
     * vod_quality : 0
     * specific_catalog : wog
     * specific_status : 1
     * credit_illegal : 0
     * is_white_list : 0
     * cur_credit : 12
     * low_credit : 4
     * url : /wog
     * game_url : /directory/game/classic
     * game_icon_url : https://staticlive.douyucdn.cn/upload/game_cate/568029e11adb524c778da572b578f2e3.jpg
     * show_details : 参与方式入群看公告——鬼叔群号：567790209      口令：鬼叔威武 |||||
     * 主播直播时间：10：00-22：00
     * 下载及所有问题可入群求助
     * owner_avatar : https://apic.douyucdn.cn/upload/avatar/006/62/01/92_avatar_big.jpg?rltime
     * cdnsWithName : [{"name":"主线路","cdn":"ws"},{"name":"备用线路5","cdn":"tct"},{"name":"备用线路2","cdn":"ws2"},{"name":"备用线路3","cdn":"dl"}]
     * is_pass_player : 0
     * owner_weight : 14.02t
     * fans : 24650
     * is_high_game : 1
     * column_id : 1
     */
    //    banner特有
    public String vod_quality;
    public String specific_catalog;
    public String specific_status;
    public String credit_illegal;
    public String is_white_list;
    public String cur_credit;
    public String low_credit;
    public String url;
    public String game_url;
    public String game_icon_url;
    public String show_details;
    public String owner_avatar;
    public int is_pass_player;
    public String owner_weight;
    public String fans;
    public int is_high_game;
    public String column_id;
    public List<CdnsWithNameBean> cdnsWithName;



    public static class IconDataBean {
        /**
         * status : 5
         * icon_url :
         * icon_width : 0
         * icon_height : 0
         */

        public int status;
        public String icon_url;
        public int icon_width;
        public int icon_height;
    }

    public static class CdnsWithNameBean {
        /**
         * name : 主线路
         * cdn : ws
         */

        public String name;
        public String cdn;

    }
}
