package com.chunze.usercenter.presenter

import com.chunze.baselibrary.ext.execute
import com.chunze.baselibrary.presenter.BasePresenter
import com.chunze.baselibrary.rx.BaseSubscriber
import com.chunze.usercenter.presenter.view.RegisterView
import com.chunze.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/1.
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserService

    fun register(userName: String, password: String, code: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.register(userName, password, code)
                .execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.showResult(t)
                    }
                })

    }

}