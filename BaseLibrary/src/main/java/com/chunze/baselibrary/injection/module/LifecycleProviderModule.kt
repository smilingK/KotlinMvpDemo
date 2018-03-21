package com.chunze.baselibrary.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Administrator on 2018/2/27.
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providerLifecycle(): LifecycleProvider<*> {

        return lifecycleProvider
    }
}