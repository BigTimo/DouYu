package com.app.douyu.net;

import com.app.douyu.bean.BaseResult;
import com.app.douyu.bean.home.HomeRecommendHotCate;
import com.app.douyu.bean.home.HomeTitle;
import com.app.douyu.net.api.DouYuService;
import com.google.gson.Gson;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author Free
 * @version 1.0
 * @since 2017/2/23
 */
public class HttpManager {


    private static DouYuService getYWService() {
        return HttpConfig.getInstance().getDouYuService();
    }


    /**
     * 统一的请求结果处理
     */
    private static <T> Subscription handleResult(Observable<BaseResult<T>> observable, final BaseCallBack<T> subscriber) {
        return observable
                .map(new Func1<BaseResult<T>, T>() {
                    @Override
                    public T call(BaseResult<T> result) {
                        if (result.error != 0) {
                            subscriber.onError(new ApiException(result));
                        }
                        Timber.d("接收到数据===================> %s",new Gson().toJson(result.data));
                        return result.data;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public static Subscription getHomeCateList(BaseCallBack<List<HomeTitle>> subscriber) {
        return handleResult(getYWService().getHomeCateList(), subscriber);
    }

    public static Subscription getHomeCate(String identification, BaseCallBack<List<HomeRecommendHotCate>> subscriber) {
        return handleResult(getYWService().getHomeCate(identification), subscriber);
    }


}
