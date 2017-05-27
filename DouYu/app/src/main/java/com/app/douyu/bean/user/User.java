package com.app.douyu.bean.user;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/27
 */
public class User {

    /**
     * uid : 89175431
     * username : 89175431
     * nickname : 377413612
     * email : false
     * qq :
     * mobile_phone : 008*********3224
     * phone_status : 1
     * email_status : 0
     * lastlogin : 1495796516
     * avatar : {"small":"https://apic.douyucdn.cn/upload/avanew/face/201703/15/16/b23c94e4a22477f2e6267253a907fe6b_small.jpg?rltime","middle":"https://apic.douyucdn.cn/upload/avanew/face/201703/15/16/b23c94e4a22477f2e6267253a907fe6b_middle.jpg?rltime","big":"https://apic.douyucdn.cn/upload/avanew/face/201703/15/16/b23c94e4a22477f2e6267253a907fe6b_big.jpg?rltime"}
     * has_room : 0
     * groupid : 1
     * is_own_room : 0
     * location : {"province":"北京","city":""}
     * sex : 1
     * birthday : 19940101
     * is_reg_by_third : 0
     * gold1 : 0
     * score : 0
     * level : {"current":{"lv":1,"pic":"user1.gif","mpic":"cn01.png","name":"菜鸟","pic_url":"https://staticlive.douyucdn.cn/common/douyu/images/classimg/user1.gif?v=v83858","score":0},"next":{"lv":2,"pic":"user2.gif","mpic":"brass05.png","name":"黄铜5","pic_url":"https://staticlive.douyucdn.cn/common/douyu/images/classimg/user2.gif?v=v83858","score":100}}
     * userlevel : {"cur_score":76,"next_level_score":106,"lv":3,"is_full":0}
     * follow : 12
     * ios_gold_switch : 1
     * gold : 0.0
     * noble_gold : 0.0
     * ident_status : 0
     * token : 89175431_a38ae9465c1ed612
     * token_exp : 1496715237
     */

    public String uid;
    public String username;
    public String nickname;
    public boolean email;
    public String qq;
    public String mobile_phone;
    public String phone_status;
    public String email_status;
    public String lastlogin;
    public AvatarBean avatar;
    public String has_room;
    public String groupid;
    public String is_own_room;
    public LocationBean location;
    public String sex;
    public String birthday;
    public int is_reg_by_third;
    public String gold1;
    public String score;
    public LevelBean level;
    public UserlevelBean userlevel;
    public String follow;
    public int ios_gold_switch;
    public String gold;
    public String noble_gold;
    public String ident_status;
    public String token;
    public int token_exp;


    public static class AvatarBean {
        /**
         * small : https://apic.douyucdn.cn/upload/avanew/face/201703/15/16/b23c94e4a22477f2e6267253a907fe6b_small.jpg?rltime
         * middle : https://apic.douyucdn.cn/upload/avanew/face/201703/15/16/b23c94e4a22477f2e6267253a907fe6b_middle.jpg?rltime
         * big : https://apic.douyucdn.cn/upload/avanew/face/201703/15/16/b23c94e4a22477f2e6267253a907fe6b_big.jpg?rltime
         */

        public String small;
        public String middle;
        public String big;


    }

    public static class LocationBean {
        /**
         * province : 北京
         * city :
         */

        public String province;
        public String city;


    }

    public static class LevelBean {
        /**
         * current : {"lv":1,"pic":"user1.gif","mpic":"cn01.png","name":"菜鸟","pic_url":"https://staticlive.douyucdn.cn/common/douyu/images/classimg/user1.gif?v=v83858","score":0}
         * next : {"lv":2,"pic":"user2.gif","mpic":"brass05.png","name":"黄铜5","pic_url":"https://staticlive.douyucdn.cn/common/douyu/images/classimg/user2.gif?v=v83858","score":100}
         */

        public CurrentBean current;
        public NextBean next;
    }

    public static class CurrentBean {
        /**
         * lv : 1
         * pic : user1.gif
         * mpic : cn01.png
         * name : 菜鸟
         * pic_url : https://staticlive.douyucdn.cn/common/douyu/images/classimg/user1.gif?v=v83858
         * score : 0
         */

        public int lv;
        public String pic;
        public String mpic;
        public String name;
        public String pic_url;
        public int score;
    }

    public static class NextBean {
        /**
         * lv : 2
         * pic : user2.gif
         * mpic : brass05.png
         * name : 黄铜5
         * pic_url : https://staticlive.douyucdn.cn/common/douyu/images/classimg/user2.gif?v=v83858
         * score : 100
         */

        public int lv;
        public String pic;
        public String mpic;
        public String name;
        public String pic_url;
        public int score;

    }

    public static class UserlevelBean {
        /**
         * cur_score : 76
         * next_level_score : 106
         * lv : 3
         * is_full : 0
         */

        public int cur_score;
        public int next_level_score;
        public int lv;
        public int is_full;

    }
}
