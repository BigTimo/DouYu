package com.app.douyu.ui.play;

import com.app.douyu.base.BasePresenter;
import com.app.douyu.base.IBaseView;
import com.app.douyu.bean.play.OldLiveVideoInfo;

/**
 * @author Free
 * @version 1.0
 * @since 2017/6/14
 */
public interface PlayContract {

    interface PlayView extends IBaseView {


        void showPlay(OldLiveVideoInfo liveVideoInfo);
    }


    abstract class Presenter extends BasePresenter<PlayView> {
        abstract void getRoomInfo(String room_id);
    }


}
