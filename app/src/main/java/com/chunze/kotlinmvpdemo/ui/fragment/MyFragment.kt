package com.chunze.kotlinmvpdemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chunze.baselibrary.ext.loadUrl
import com.chunze.baselibrary.ext.onClick
import com.chunze.baselibrary.ui.fragment.BaseFragment
import com.chunze.baselibrary.utils.AppPrefsUtils
import com.chunze.kotlinmvpdemo.R
import com.chunze.kotlinmvpdemo.common.isLogin
import com.chunze.kotlinmvpdemo.ui.activity.SettingActivity
import com.chunze.provider.common.ProviderConstant
import com.chunze.usercenter.ui.LoginActivity
import com.chunze.usercenter.ui.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_my.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Administrator on 2018/3/8.
 */
class MyFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_my, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        initData()

    }

    private fun initData() {

        if (isLogin()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogin()) {
                    startActivity<UserInfoActivity>()
                } else {
                    startActivity<LoginActivity>()
                }
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()

            }
        }

    }

}