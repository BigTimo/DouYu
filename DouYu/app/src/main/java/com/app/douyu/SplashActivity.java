package com.app.douyu;

import android.content.Intent;
import android.os.Bundle;

import com.app.douyu.base.BaseActivity;
import com.app.douyu.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Free
 * @version 1.0
 * @since 2017/5/26
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected boolean setUseLayoutIdCustom() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                });

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
