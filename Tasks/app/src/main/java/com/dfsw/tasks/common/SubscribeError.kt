package com.dfsw.tasks.common

import android.util.Log

/**
 * Object to handle Disposable's error.
 */
object SubscribeError {

    /**
     * Called from subscribes to handle onError status.
     */
    fun onError(throwable: Throwable, tag: String) {
        throwable.message?.let { messageError ->
            Log.e(tag, messageError)
        } ?: run {
            Log.e(tag, throwable.localizedMessage)
        }
    }
}
