package com.app.douyu.bean.play;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：   直播老接口
 *  备注消息：
 *  修改时间：2017/3/9 下午1:19
 **/
public class OldLiveVideoInfo {


    /**
     * error : 0
     * data : {"rtmp_cdn":"ws","room_id":"79631","room_name":"明皇直播：韩国训练第6天播到11点","rateSwitch":1,"hls_url":"http://hls3.douyucdn.cn/live/79631rwT9hvGtWgJ/playlist.m3u8?wsSecret=f20b8082924172fbd5cab393335e0402&wsTime=1489036472","live_url":"http://hdl3.douyucdn.cn/live/79631rwT9hvGtWgJ.flv?wsAuth=4484fe0cb51acf407d640e017b85d42c&token=cpn-pcclient-905619-79631-5189de0c9dc4e4e5f942a9b2a507f355&logo=0&expire=0"}
     */

    public int error;
    /**
     * rtmp_cdn : ws
     * room_id : 79631
     * room_name : 明皇直播：韩国训练第6天播到11点
     * rateSwitch : 1
     * hls_url : http://hls3.douyucdn.cn/live/79631rwT9hvGtWgJ/playlist.m3u8?wsSecret=f20b8082924172fbd5cab393335e0402&wsTime=1489036472
     * live_url : http://hdl3.douyucdn.cn/live/79631rwT9hvGtWgJ.flv?wsAuth=4484fe0cb51acf407d640e017b85d42c&token=cpn-pcclient-905619-79631-5189de0c9dc4e4e5f942a9b2a507f355&logo=0&expire=0
     */

    public DataEntity data;


    public static class DataEntity {
        public String rtmp_cdn;
        public String room_id;
        public String room_name;
        public int rateSwitch;
        public String hls_url;
        public String live_url;

    }
}
