package com.app.douyu.ui.play;

import android.util.Log;

import com.app.douyu.bean.play.OldLiveVideoInfo;
import com.app.douyu.net.api.ApiConfig;
import com.app.douyu.util.MD5Util;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Free
 * @version 1.0
 * @since 2017/6/14
 */
public class PlayPresenter extends PlayContract.Presenter {
    @Override
    void getRoomInfo(String room_id) {


        /**
         * 房间加密处理
         */
        int time = (int) (System.currentTimeMillis() / 1000);
        String str = "lapi/live/thirdPart/getPlay/" + room_id + "?aid=pcclient&rate=0&time=" + time + "9TUk5fjjUjg9qIMH3sdnh";
        String auth = MD5Util.getToMd5Low32(str);
        Request requestPost = new Request.Builder()
                .url(ApiConfig.oldBaseUrl + ApiConfig.getOldLiveVideo + room_id + "?rate=0")
                .get()
                .addHeader("aid", "pcclient")
                .addHeader("auth", auth)
                .addHeader("time", time + "")
                .build();


        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        client.newCall(requestPost).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                String json = response.body().string().toString();
                Log.e("onResponse", json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("error") == 0) {
                        Gson gson = new Gson();
                        final OldLiveVideoInfo mLiveVideoInfo = gson.fromJson(json, OldLiveVideoInfo.class);
                        Observable.create(new Observable.OnSubscribe<OldLiveVideoInfo>() {
                            @Override
                            public void call(Subscriber<? super OldLiveVideoInfo> subscriber) {
                                subscriber.onNext(mLiveVideoInfo);
                            }
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<OldLiveVideoInfo>() {
                                    @Override
                                    public void call(OldLiveVideoInfo liveVideoInfo) {
                                        mView.showPlay(liveVideoInfo);
                                    }
                                });


                    } else {
                        //                        mView.showErrorWithStatus("获取数据失败!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
