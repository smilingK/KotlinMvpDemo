package com.chunze.baselibrary.injection.module

import android.content.Context
import com.chunze.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Administrator on 2018/2/27.
 */
@Module
class AppModule (private val context: BaseApplication){

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}