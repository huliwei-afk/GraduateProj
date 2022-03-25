package com.example.graduateproj.commonUtil

import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import kotlin.Throws
import io.reactivex.rxjava3.core.ObservableEmitter
import java.lang.NullPointerException

object RxClickUtil {
    fun clickEvent(view: View): Observable<*> {
        checkViewNotNull(view)
        return Observable.create(ViewClickOnSubscribe(view))
    }

    private fun checkViewNotNull(view: View?) {
        if (view == null) throw NullPointerException("Null View can not be clicked!")
    }

    private class ViewClickOnSubscribe(private val view: View) : ObservableOnSubscribe<Int?> {
        @Throws(Throwable::class)
        override fun subscribe(emitter: ObservableEmitter<Int?>) {
            view.setOnClickListener {
                if (!emitter.isDisposed) {
                    emitter.onNext(1)
                }
            }
        }
    }
}