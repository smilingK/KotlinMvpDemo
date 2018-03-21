package com.chunze.baselibrary.ui.activity

import android.os.Bundle
import com.chunze.baselibrary.common.AppManger
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * Created by Administrator on 2018/2/24.
 */
open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManger.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManger.instance.finishActivity(this)
    }
}