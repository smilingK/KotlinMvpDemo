package com.chunze.baselibrary.injection.component

import android.content.Context
import com.chunze.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Administrator on 2018/2/27.
 */
@Singleton
@Component( modules = [(AppModule::class)])
interface AppComponent {

    fun context(): Context

}