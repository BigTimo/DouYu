package com.app.douyu.ui.live;

import com.app.douyu.base.BasePresenter;
import com.app.douyu.base.IBaseView;
import com.app.douyu.bean.home.RoomListEntity;
import com.app.douyu.bean.live.LiveTitle;
import com.app.douyu.bean.live.LiveTitleTwo;

import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/6/11
 */
public interface LiveContract {

    interface TitleView extends IBaseView {
        void showTitles(List<LiveTitle> titles);
    }

    interface DetailView extends IBaseView {

        void showTitlesTow(List<LiveTitleTwo> result);

        void showLiveDetail(List<RoomListEntity> result);
    }


    abstract class Presenter extends BasePresenter<IBaseView> {

        public abstract void getLiveTitles();

        public abstract void getLiveTitlesTwo(String shortName);


        public abstract void getLiveDetail(String tag_id);


        public abstract void getLiveAll();

        public abstract void getLivSports();


    }


}
