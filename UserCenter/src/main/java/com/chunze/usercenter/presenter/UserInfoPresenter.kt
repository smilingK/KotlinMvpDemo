package com.chunze.usercenter.presenter

import com.chunze.baselibrary.ext.execute
import com.chunze.baselibrary.presenter.BasePresenter
import com.chunze.baselibrary.rx.BaseSubscriber
import com.chunze.usercenter.data.protocol.UserInfo
import com.chunze.usercenter.presenter.view.UserInfoView
import com.chunze.usercenter.service.UserService
import com.chunze.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserService

    fun editUserInfo(userIcon: String, userName: String, gender: String, sign: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.editUserInfo(userIcon, userName, gender, sign)
                .execute(lifecycleProvider, object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        super.onNext(t)
                        UserPrefsUtils.putUserInfo(t)
                        mView.requestResults(true)
                    }
                })
    }

}