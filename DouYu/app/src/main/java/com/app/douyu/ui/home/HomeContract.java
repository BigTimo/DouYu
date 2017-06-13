package com.app.douyu.ui.home;

import com.app.douyu.base.BasePresenter;
import com.app.douyu.base.IBaseView;
import com.app.douyu.bean.home.HomeBanner;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.bean.home.HomeTitle;

import java.util.List;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/25
 */
public interface HomeContract {

    interface HomeCateListView extends IBaseView {
        void showHomeCateTitle(List<HomeTitle> result);
    }

    interface HomeCateView extends IBaseView {

        void showHomeBanner(List<HomeBanner> result);

        void showHomeCateDetail(List<HomeRecommendHotCate> result);
    }


    abstract class Presenter extends BasePresenter<IBaseView> {

        public abstract void getHomeCateList();

        public abstract void getHomeBanner();

        public abstract void getHomeCate(String identification);


    }
}
