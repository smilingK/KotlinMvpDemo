package com.chunze.messagecenter.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chunze.baselibrary.ext.onClick
import com.chunze.messagecenter.R
import com.chunze.provider.arouter.RouterPath
import kotlinx.android.synthetic.main.msg_activity_main.*
import org.jetbrains.anko.toast

@Route(path = RouterPath.MessageCenter.PATH_MSG)
class MsgMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.msg_activity_main)
        msg.onClick {
            toast("被点击")
            ARouter.getInstance().build(RouterPath.MainCenter.PATH_SETTING).navigation()
        }
    }
}
