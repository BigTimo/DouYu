package com.app.douyu.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.app.douyu.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.HashMap;
import java.util.Stack;

import timber.log.Timber;


/**
 * @author Free
 * @version 1.0
 * @since 2017/1/5
 */
public class App extends Application {
    private static App mApp;
    //activity栈
    public Stack<Activity> store;
    //activity跳转传递的数据
    public HashMap<String, Object> mCurActivityExtra;

    public static App getContext() {
        if (mApp == null) {
            throw new RuntimeException("NullPointException caught at app," +
                    "check AndroidManifest.xml  <application android:name= (this app)>");
        }
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        store = new Stack<>();
        registerActivityLifecycleCallbacks(new LifeCallback());
        initTimber();
        //        DaoManager.getInstance();
        initBugly();
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "e51d8ad25a", BuildConfig.DEBUG);
    }
    //    @Override
    //    protected void attachBaseContext(Context context) {
    //        super.attachBaseContext(context);
    //        Multidex.install(this);
    //    }

    /**
     * 初始化本地日志模式和内存分析插件
     */
    private void initTimber() {
        if (BuildConfig.DEBUG) { // debug模式打印日志并使用本地内存泄漏跟踪
            Timber.plant(new Timber.DebugTree());
            //			LeakCanary.install(this);
        } else {
            // release模式上传日志到服务器
            //            Timber.plant(new CrashReportingTree());
        }
    }

    private class LifeCallback implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            store.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity);
        }
    }

    public Activity getCurrentActivity() {
        return store.lastElement();
    }

    //    public Activity getActivity(Class<?> activity){
    //        for (Activity ac  : store) {
    //           if(ac.) {
    //
    //           }
    //        }
    //
    //    }


    public void finishAll() {
        for (Activity activity : store) {
            if (activity != null) {
                activity.finish();
            }
        }
        store.clear();
    }


}
