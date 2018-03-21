package com.chunze.kotlinmvpdemo.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chunze.baselibrary.ext.onClick
import com.chunze.baselibrary.ui.activity.BaseActivity
import com.chunze.kotlinmvpdemo.R
import com.chunze.provider.arouter.RouterPath
import com.chunze.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created by Administrator on 2018/3/8.
 */
@Route(path = RouterPath.MainCenter.PATH_SETTING)
class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    private fun initView() {
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}