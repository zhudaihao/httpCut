package cn.zdh.httpdemo;

import java.util.Map;

/**
 * 网络抽象层  房产公司
 */

public interface IHttpProcessor {
    //网络访问： post get del update put ...
    void post(String url, Map<String, Object> params, ICallback callback);

    void get(String url, Map<String, Object> params, ICallback callback);
}
