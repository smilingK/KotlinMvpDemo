package com.chunze.baselibrary.rx

import com.chunze.baselibrary.R
import com.chunze.baselibrary.common.BaseApplication.Companion.context
import com.chunze.baselibrary.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by Administrator on 2018/2/26.
 */
open class BaseSubscriber<T>(val view: BaseView) : Subscriber<T>() {

    override fun onNext(t: T) {}

    override fun onCompleted() {
        view.hideLoading()
    }

    override fun onError(e: Throwable?) {
        view.hideLoading()
        if (e is BaseException) {
            view.showError(e.msg)
        }else{
            view.showError(context.getString(R.string.data_exception))
        }
    }
}