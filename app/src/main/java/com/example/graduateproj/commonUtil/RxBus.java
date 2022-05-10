package com.example.graduateproj.commonUtil;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RxBus {
    private static volatile RxBus sInstance;
    private final PublishSubject<Object> mEventBus = PublishSubject.create();

    public static RxBus getInstance(){
        if (sInstance == null){
            synchronized (RxBus.class){
                if (sInstance == null){
                    sInstance = new RxBus();
                }
            }
        }
        return sInstance;
    }

    /**
     * 发送事件(post event)
     * @param event : event object(事件的内容)
     */
    public void post(Object event){
        mEventBus.onNext(event);
    }

    /**
     * 返回Event的管理者,进行对事件的接受
     * @return
     */
    public Observable toObservable(){
        return mEventBus;
    }

    /**
     *
     * @param cls :保证接受到制定的类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> cls){
        //ofType起到过滤的作用,确定接受的类型
        return mEventBus.ofType(cls);
    }
}
