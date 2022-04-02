package com.example.graduateproj.commonUtil;

import android.util.Log;

import com.example.graduateproj.interfaceUtil.HttpRequest;
import com.example.graduateproj.interfaceUtil.OnDataObtainListener;
import com.example.graduateproj.mainPack.homePack.model.BannerImageBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxOkHttpUtil {

    private final String TAG = RxOkHttpUtil.class.getSimpleName();

    private static final RxOkHttpUtil INSTANCE = new RxOkHttpUtil();

    public static RxOkHttpUtil getInstance() {
        return INSTANCE;
    }

    private RxOkHttpUtil() {

    }

    // 创建OkHttp实例
    private OkHttpClient createOkHttp() {
        return new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpLoggingInterceptor(message -> {
                    if (message != null) {
                        Log.d(TAG, message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    // 创建Retrofit实例
    private Retrofit createRetrofit(final String path) {
        return new Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
                .client(createOkHttp())
                .build();
    }

    // 创建HttpRequest请求
    private HttpRequest createRequest(final String path) {
        return createRetrofit(path).create(HttpRequest.class);
    }

    public void syncHttpRequest(final String path, final OnDataObtainListener onDataObtainListener) {
        createRequest(path)
                .call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BannerImageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onSuccess(@NonNull BannerImageBean bean) {
                        if (onDataObtainListener != null) {
                            onDataObtainListener.onSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (onDataObtainListener != null) {
                            onDataObtainListener.onFailure(e);
                        }
                    }
                });
    }
}
