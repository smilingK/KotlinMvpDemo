package com.chunze.baselibrary.injection.module

import android.app.Activity
import com.chunze.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by Administrator on 2018/2/27.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun providesActivity(): Activity {
        return activity
    }
}