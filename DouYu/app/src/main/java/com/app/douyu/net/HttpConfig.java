package com.app.douyu.net;

import android.util.Log;

import com.app.douyu.base.App;
import com.app.douyu.net.api.ApiConfig;
import com.app.douyu.net.api.DouYuService;
import com.app.douyu.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络配置类
 *
 * @author Free
 * @version 1.0
 * @since 2017/1/8
 */
public class HttpConfig {


    /**
     * 消息头设置
     */
    private String HEADER = "Accept";
    private String HEADER_VALUE = "*/*";

    /**
     * 超时设置
     */
    private int TIMEOUT_CONNECT = 30;
    private int TIMEOUT_READ = 30;
    private int TIMEOUT_WRITE = 30;


    private final OkHttpClient mClient;
    private DouYuService mDouYuService;
    private static HttpConfig httpConfig;


    //------------------------------------获取对应的service------------------------------------


    private HttpConfig() {
        // 拦截器，请给所有求添加参数和请求头
        Interceptor headInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                //添加通用的query
                HttpUrl url = original.url().newBuilder()
                        .addQueryParameter("client_sys", "client_sys")
                        .addQueryParameter("aid", "android1")
                        .addQueryParameter("time", System.currentTimeMillis() + "")
                        .build();

                //添加通用的请求头
                Request request = original.newBuilder()
                        .url(url)
                        .addHeader("HEAD", "head")
                        .build();

                return chain.proceed(request);
            }
        };


        //缓存文件,大小,100M
        File cacheFile = new File(App.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);


        mClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addInterceptor(headInterceptor)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();
    }

    public static HttpConfig getInstance() {
        if (httpConfig == null) {
            synchronized (HttpConfig.class) {
                if (httpConfig == null) {
                    httpConfig = new HttpConfig();
                }
            }
        }
        return httpConfig;
    }


    private Retrofit getRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public DouYuService getDouYuService() {
        if (mDouYuService == null) {
            mDouYuService = getRetrofit(ApiConfig.BASE_url, mClient).create(DouYuService.class);
        }
        return mDouYuService;
    }


    private class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(App.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(App.getContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }


}
