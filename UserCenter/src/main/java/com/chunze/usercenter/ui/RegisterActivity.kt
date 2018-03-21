package com.chunze.usercenter.ui

import android.os.Bundle
import android.view.View
import com.chunze.baselibrary.ext.enabledDetection
import com.chunze.baselibrary.ext.onClick
import com.chunze.baselibrary.ui.activity.BaseMvpActivity
import com.chunze.baselibrary.utils.StringUtils.filterEditTextInhibitInput
import com.chunze.baselibrary.utils.StringUtils.isMobile
import com.chunze.usercenter.R
import com.chunze.usercenter.injection.component.DaggerUserComponent
import com.chunze.usercenter.injection.module.UserModule
import com.chunze.usercenter.presenter.RegisterPresenter
import com.chunze.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * Created by Administrator on 2018/3/1.
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
    }

    private fun initView() {
        mRegisterBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)

        //添加EditText输入过滤器
        filterEditTextInhibitInput(mPwdEt)
        filterEditTextInhibitInput(mPwdConfirmEt)

        //检测输入框是否有值，控制按照是否启用
        mRegisterBtn.enabledDetection(mMobileEt, { btnIsEnabled() })
        mRegisterBtn.enabledDetection(mVerifyCodeEt, { btnIsEnabled() })
        mRegisterBtn.enabledDetection(mPwdEt, { btnIsEnabled() })
        mRegisterBtn.enabledDetection(mPwdConfirmEt, { btnIsEnabled() })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mVerifyCodeBtn -> {
                //判断是否是手机号码
                if (isPhoneNumber()) {
                    mVerifyCodeBtn.requestSendVerifyNumber()
                } else {
                    mMobileEt.error = getString(R.string.Please_enter_your_mobile_number)
                }
            }
            R.id.mRegisterBtn -> {
                submitData()
            }
        }
    }

    /**
     * 提交注册数据
     */
    private fun submitData() {
        val mobile = mMobileEt.text.toString().trim()
        val code = mVerifyCodeEt.text.toString().trim()
        val pwd = mPwdEt.text.toString().trim()
        val pwdConfirm = mPwdConfirmEt.text.toString().trim()

        //判断密码位数
        when {
            pwd.length < 6 -> {
                mPwdEt.error = getString(R.string.error_invalid_password)
                toast(getString(R.string.Please_enter_your_password))
            }
            pwd == pwdConfirm -> mPresenter.register(mobile, pwd, code)
            else -> mPwdConfirmEt.error = getString(R.string.Two_passwords_inconsistent)
        }
    }


    override fun showResult(result: Boolean) {

        if (result) {
            toast(getString(R.string.Register_successfully))
            startActivity(intentFor<LoginActivity>().singleTop().clearTop())
        }
    }


    override fun initInjection() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    //判断是否是手机号码
    private fun isPhoneNumber(): Boolean {
        val mobile = mMobileEt.text.toString().trim()
        return mobile.isEmpty().not() && isMobile(mobile)
    }

    //判断所以输入框是否输入
    private fun btnIsEnabled(): Boolean {
        return mMobileEt.text.isEmpty().not()
                && mVerifyCodeEt.text.isEmpty().not()
                && mPwdEt.text.isEmpty().not()
                && mPwdConfirmEt.text.isEmpty().not()
    }

}