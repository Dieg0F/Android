package com.dfsw.tasks.common

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Thread related methods.
 */
object ThreadUtils {

    /**
     * Use a timer to execute an expression after a given time in milliseconds.
     */
    fun postDelayed(waitTime: Long, expression: () -> Unit): Disposable =
        Observable.timer(waitTime, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .map { expression() }
            .subscribe({}, { SubscribeError.onError(it, Logger.TAG) })
}
