package soexample.umeng.com.zjh.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 1.无参构造
 * 2.静态类方法
 * 3.静态内部类
 */

public class OkhttpUtils {

    private OkHttpClient okHttpClient;

    public OkhttpUtils() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();  //日志拦截器
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(interceptor)   //添加日志拦截器
                .build();
    }

    public static OkhttpUtils getInstance(){
        return OkHolder.okhttpUtils;
    }

    static class OkHolder{
        private static final OkhttpUtils okhttpUtils = new OkhttpUtils();
    }


    //封装
    public void getAsync(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
