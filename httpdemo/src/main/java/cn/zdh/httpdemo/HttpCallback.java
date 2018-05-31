package cn.zdh.httpdemo;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 回调接口的实现
 * 1:必需想办法得到Result
 * 2:用户把 返回的string转换成调用层传入的参数化类型Result
 */

public abstract class HttpCallback<Result> implements ICallback {
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        //clz 就是用户输入的类型对应的字节码
        Class<?> clz = analysisClassInfo(this);
        Result objResult = (Result) gson.fromJson(result, clz);
        //objResult传回给调用者
        onSuccess(objResult);

    }

    //重写成功方法
    public abstract void onSuccess(Result result);

    private static Class<?> analysisClassInfo(Object object) {
        //getGenericSuperclass()可以得到包含原始类型，参数化类型，数组类型，类型变量
        Type genericSuperclass = object.getClass().getGenericSuperclass();
        //获取参数化类型Result
        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();

        return (Class<?>) actualTypeArguments[0];
    }

    @Override
    public void onFailure(String e) {

    }
}
