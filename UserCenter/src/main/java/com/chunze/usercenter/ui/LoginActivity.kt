package com.chunze.usercenter.ui

import android.os.Bundle
import android.view.View
import com.chunze.baselibrary.ext.enabledDetection
import com.chunze.baselibrary.ext.onClick
import com.chunze.baselibrary.ext.onPerfectClick
import com.chunze.baselibrary.ui.activity.BaseMvpActivity
import com.chunze.usercenter.R
import com.chunze.usercenter.data.protocol.UserInfo
import com.chunze.usercenter.injection.component.DaggerUserComponent
import com.chunze.usercenter.injection.module.UserModule
import com.chunze.usercenter.presenter.LoginPresenter
import com.chunze.usercenter.presenter.view.LoginView
import com.chunze.usercenter.utils.UserPrefsUtils
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initInjection()
        initView()
    }

    private fun initView() {
        mHeaderBar.getRightTv().setOnClickListener(this)
        mLoginBtn.enabledDetection(email, { isBtnEnabled() })
        mLoginBtn.enabledDetection(password, { isBtnEnabled() })
        mLoginBtn.onClick { login() }

        RxView.clicks(mLoginBtn).onPerfectClick { login() }
    }

    private fun login() {
        mPresenter.login(email.text.toString(), password.text.toString(), "")
    }


    override fun loginResult(result: UserInfo) {
        toast("登录成功!")
        UserPrefsUtils.putUserInfo(result)
        finish()
    }

    override fun showError(msg: String) {
        super.showError(msg)
        toast(msg)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
        }
    }

    override fun initInjection() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    private fun isBtnEnabled(): Boolean {
        return email.text.isEmpty().not() && password.text.isEmpty().not()
    }
}


