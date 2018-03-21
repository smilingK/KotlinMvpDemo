package com.chunze.baselibrary.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.chunze.baselibrary.injection.component.AppComponent
import com.chunze.baselibrary.injection.component.DaggerAppComponent
import com.chunze.baselibrary.injection.module.AppModule

/**
 * Created by Administrator on 2018/2/27.
 */
open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
        context = this
        ARouter.openLog()     // 打印日志
        ARouter.openDebug()
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    private fun initAppInjection() {

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}