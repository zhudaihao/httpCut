package cn.zdh.httpdemo;

import android.app.Application;

/**
 * 注册，切换框架
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //网络框架选择
        HttpHelper.init(new VolleyProcessor(this));
        //  HttpHelper.init(new XUtilsProcessor(this));
    }
}
