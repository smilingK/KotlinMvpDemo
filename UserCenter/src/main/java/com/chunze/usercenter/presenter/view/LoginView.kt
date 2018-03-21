package com.chunze.usercenter.presenter.view

import com.chunze.baselibrary.presenter.view.BaseView
import com.chunze.usercenter.data.protocol.UserInfo

/**
 * Created by Administrator on 2018/2/24.
 */
interface LoginView : BaseView {
    fun loginResult(result: UserInfo)
}