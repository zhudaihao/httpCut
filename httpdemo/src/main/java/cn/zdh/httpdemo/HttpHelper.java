package cn.zdh.httpdemo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 卖房公司的销售员
 */

public class HttpHelper implements IHttpProcessor {
    //持有一个有房人的引用 （卖房人实现了IHttpProcessor，多态可以切换多个卖房人）
    private static IHttpProcessor mIHttpProcessor = null;

    public static void init(IHttpProcessor httpProcessor) {
        mIHttpProcessor = httpProcessor;
    }

    //单利
    private static HttpHelper instance;

    private HttpHelper() {
    }

    public static HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (instance == null) {
                instance = new HttpHelper();
            }
        }

        return instance;
    }



    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        String urlPath = appendParams(url, params);
        mIHttpProcessor.post(urlPath, params, callback);

    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {
        mIHttpProcessor.get(url, params, callback);
    }


    public static String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlBuilder.indexOf("?") <= 0) {
            urlBuilder.append("?");

        } else {
            if (!urlBuilder.toString().endsWith("?")) {
                urlBuilder.append("&");
            }
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            urlBuilder.append("&" + entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }

        return urlBuilder.toString();
    }


    private static String encode(String str) {

        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
