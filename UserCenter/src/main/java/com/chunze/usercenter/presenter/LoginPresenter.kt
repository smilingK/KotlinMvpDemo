package com.chunze.usercenter.presenter

import com.chunze.baselibrary.ext.execute
import com.chunze.baselibrary.presenter.BasePresenter
import com.chunze.baselibrary.rx.BaseSubscriber
import com.chunze.usercenter.data.protocol.UserInfo
import com.chunze.usercenter.presenter.view.LoginView
import com.chunze.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by Administrator on 2018/2/24.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserService

    fun login(userName: String, password: String, pushId: String) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.login(userName, password, pushId)
                .execute(lifecycleProvider, object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        super.onNext(t)
                        mView.loginResult(t)
                    }
                })
    }

}