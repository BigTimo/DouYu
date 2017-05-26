package com.app.douyu.bean;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public class BaseResult<T> {


    /**
     * error : 0
     * data : [{"title":"手游","show_order":"1","identification":"3e760da75be261a588c74c4830632360","is_video":0,"is_show_cate1_icon":0},{"title":"娱乐","show_order":"2","identification":"9acf9c6f117a4c2d02de30294ec29da9","is_video":0,"is_show_cate1_icon":0},{"title":"游戏","show_order":"3","identification":"ba08216f13dd1742157412386eee1225","is_video":0,"is_show_cate1_icon":0},{"title":"趣玩","show_order":"4","identification":"393b245e8046605f6f881d415949494c","is_video":0,"is_show_cate1_icon":0}]
     */

    public int error;
    public T data;


}
