package com.chunze.kotlinmvpdemo.common

import com.chunze.baselibrary.common.BaseConstant
import com.chunze.baselibrary.utils.AppPrefsUtils

/**
 * Created by Administrator on 2018/3/8.
 */
fun isLogin(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotBlank()
}