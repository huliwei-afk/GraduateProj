package com.example.graduateproj.commonUtil;

import android.util.Log;

import com.example.graduateproj.interfaceUtil.HttpRequest;
import com.example.graduateproj.interfaceUtil.InterfacesHolder;
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean;
import com.example.graduateproj.mainPack.homePack.model.BannerImageBean;
import com.example.graduateproj.mainPack.homePack.model.ElectricBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
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

    public void syncHttpRequestForBanner(final String path, final InterfacesHolder.OnBannerDataObtainListener onBannerDataObtainListener) {
        createRequest(path)
                .callBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BannerImageBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onSuccess(@NonNull BannerImageBean bean) {
                        if (onBannerDataObtainListener != null) {
                            onBannerDataObtainListener.onSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (onBannerDataObtainListener != null) {
                            onBannerDataObtainListener.onFailure(e);
                        }
                    }
                });
    }

    public void syncHttpRequestForDonate(final String path, final InterfacesHolder.OnDonateDataObtainListener onDonateDataObtainListener) {
        createRequest(path)
                .callDonate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DonateJsonBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onNext(@NonNull DonateJsonBean donateJsonBean) {
                        if (onDonateDataObtainListener != null) {
                            onDonateDataObtainListener.onNext(donateJsonBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (onDonateDataObtainListener != null) {
                            onDonateDataObtainListener.onFailure(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void syncHttpRequestForElectric(final String path, final InterfacesHolder.OnElectricDataObtainListener onElectricDataObtainListener) {
        createRequest(path)
                .callElectric()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ElectricBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }

                    @Override
                    public void onSuccess(@NonNull ElectricBean bean) {
                        if (onElectricDataObtainListener != null) {
                            onElectricDataObtainListener.onSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (onElectricDataObtainListener != null) {
                            onElectricDataObtainListener.onFailure(e);
                        }
                    }
                });
    }
}
