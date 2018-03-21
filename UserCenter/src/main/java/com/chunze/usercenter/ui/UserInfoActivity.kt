package com.chunze.usercenter.ui

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.chunze.baselibrary.ext.loadUrl
import com.chunze.baselibrary.ext.onClick
import com.chunze.baselibrary.ui.activity.BaseMvpActivity
import com.chunze.baselibrary.utils.AppPrefsUtils
import com.chunze.provider.arouter.RouterPath
import com.chunze.provider.common.ProviderConstant
import com.chunze.usercenter.R
import com.chunze.usercenter.injection.component.DaggerUserComponent
import com.chunze.usercenter.injection.module.UserModule
import com.chunze.usercenter.presenter.UserInfoPresenter
import com.chunze.usercenter.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

/**
 * Created by Administrator on 2018/3/7.
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView {


    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
    }

    private fun initView() {
        mUserIconIv.onClick {
            showDialog()
        }
        mHeaderBar.getRightTv().onClick {
            ARouter.getInstance().build(RouterPath.MessageCenter.PATH_MSG).navigation()
//            mPresenter.editUserInfo(mUserIcon!!
//                    , mUserNameEt.text?.toString() ?: ""
//                    , if (mGenderFemaleRb.isChecked) "0" else "1"
//                    , mUserSignEt.text?.toString() ?: "")
        }
    }

    private fun showDialog() {
        AlertView.Builder()
                .setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("更改头像")
                .setMessage(null)
                .setCancelText("取消")
                .setDestructive("拍照", "从相册选择")
                .setOnItemClickListener { _, position ->
                    when (position) {
                        0 -> {
                            toast("点击 $position")
                        }
                        1 -> {
                            toast("点击 $position")
                        }
                    }
                }
                .build()
                .show()
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        if (mUserIcon != "") {
            mUserIconIv.loadUrl(mUserIcon!!)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderFemaleRb.isChecked = true
        } else {
            mGenderMaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)

    }

    override fun requestResults(result: Boolean) {
        if (result) {
            toast(getString(R.string.modify_successfully))
            finish()
        }
    }

    override fun initInjection() {
        DaggerUserComponent
                .builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }
}