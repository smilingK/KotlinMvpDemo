package com.chunze.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * Created by Administrator on 2018/2/27.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope