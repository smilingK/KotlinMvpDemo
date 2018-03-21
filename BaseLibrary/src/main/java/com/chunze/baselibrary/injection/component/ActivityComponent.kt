package com.chunze.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.chunze.baselibrary.injection.ActivityScope
import com.chunze.baselibrary.injection.module.ActivityModule
import com.chunze.baselibrary.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Created by Administrator on 2018/2/27.
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class), (LifecycleProviderModule::class)])
interface ActivityComponent {

    fun activity(): Activity
    fun context(): Context
    fun linfecycle(): LifecycleProvider<*>

}