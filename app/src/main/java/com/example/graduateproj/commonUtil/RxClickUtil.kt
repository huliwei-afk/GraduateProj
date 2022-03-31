package com.example.graduateproj.commonUtil

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Vibrator
import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe


object RxClickUtil {

    private var canVibrate = false
    private lateinit var vibrator: Vibrator

    fun clickEvent(view: View, context: Context): Observable<*> {
        checkViewNotNull(view)
        checkVibrator(context)
        return Observable.create(ViewClickOnSubscribe(view))
    }

    private fun checkVibrator(context: Context) {
        vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            canVibrate = true
        }
    }

    private fun checkViewNotNull(view: View?) {
        if (view == null) throw NullPointerException("Null View can not be clicked!")
    }

    private class ViewClickOnSubscribe(private val view: View) : ObservableOnSubscribe<Int> {
        @Throws(Throwable::class)
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            view.setOnClickListener {
                if (!emitter.isDisposed) {
                    if(canVibrate) {
                        vibrator.vibrate(100)
                    } else {
                        vibrator.cancel()
                    }
                    emitter.onNext(1)
                }
            }
        }
    }
}