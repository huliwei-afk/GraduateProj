package com.example.graduateproj.commonUtil

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Vibrator
import android.view.View
import com.example.graduateproj.mainPack.mePack.util.PreferStateUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe


object RxClickUtil {

    private var canVibrate = false
    private lateinit var vibrator: Vibrator

    fun clickEvent(view: View, context: Context): Observable<*> {
        checkViewNotNull(view)
        return Observable.create(ViewClickOnSubscribe(view, context))
    }

    private fun checkCanVibrate(context: Context): Boolean {
        vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator() && PreferStateUtil.getInstance(context).vibrateStateOrDefault) {
            canVibrate = true
        } else {
            canVibrate = false
            vibrator.cancel()
        }
        return canVibrate
    }

    private fun checkViewNotNull(view: View?) {
        if (view == null) throw NullPointerException("Null View can not be clicked!")
    }

    private class ViewClickOnSubscribe(private val view: View, private val context: Context): ObservableOnSubscribe<Int> {
        @Throws(Throwable::class)
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            view.setOnClickListener {
                if (!emitter.isDisposed) {
                    if (checkCanVibrate(context)) {
                        vibrator.vibrate(100)
                    }
                    emitter.onNext(1)
                }
            }
        }
    }
}