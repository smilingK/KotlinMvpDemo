package com.chunze.usercenter.injection.component

import com.chunze.baselibrary.injection.PerComponentScope
import com.chunze.baselibrary.injection.component.ActivityComponent
import com.chunze.usercenter.injection.module.UserModule
import com.chunze.usercenter.ui.LoginActivity
import com.chunze.usercenter.ui.RegisterActivity
import com.chunze.usercenter.ui.UserInfoActivity
import dagger.Component

/**
 * Created by Administrator on 2018/2/27.
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(UserModule::class)])
interface UserComponent {
    fun inject(activity: LoginActivity)
    fun inject(activity: RegisterActivity)
    fun inject(activity: UserInfoActivity)
}