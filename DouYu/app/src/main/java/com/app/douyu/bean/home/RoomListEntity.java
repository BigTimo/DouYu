package com.app.douyu.bean.home;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class RoomListEntity {


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
    public int cate_id;
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

}
