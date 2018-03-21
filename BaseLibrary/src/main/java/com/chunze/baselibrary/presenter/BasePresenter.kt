package com.chunze.baselibrary.presenter

import android.content.Context
import com.chunze.baselibrary.presenter.view.BaseView
import com.chunze.baselibrary.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Created by Administrator on 2018/2/24.
 */
open class BasePresenter<T : BaseView> {

    lateinit var mView: T
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    /*
        检查网络是否可用
     */
    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(context)) {
            return true
        }
        mView.showError("网络不可用")
        return false
    }


}