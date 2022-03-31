package com.example.graduateproj.commonUtil;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class RxOkHttpUtil {

    private final String TAG = RxOkHttpUtil.class.getSimpleName();

    interface OnDataObtainListener {
        void onSuccess(Response response);

        void onFailure(Throwable e);
    }

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

    // 创建Request请求
    private Request createRequest(final String path) {
        return new Request.Builder()
                .url(path)
                .get()
                .build();
    }

    // 做get请求
    private Single<?> doGetRequestToObservable(final String path) {
        return Single.create(emitter -> {
            Call call = createOkHttp().newCall(createRequest(path));
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    emitter.onSuccess(response);
                }
            });
        });
    }

    public void asyncHttpRequest(final String path, final OnDataObtainListener onDataObtainListener) {
        Single<?> observable = doGetRequestToObservable(path);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Object o) {
                        if (onDataObtainListener != null) {
                            onDataObtainListener.onSuccess((Response) o);
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
