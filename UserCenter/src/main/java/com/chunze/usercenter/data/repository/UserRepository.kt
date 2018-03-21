package com.chunze.usercenter.data.repository

import com.chunze.baselibrary.data.net.RetrofitFactory
import com.chunze.baselibrary.data.protocol.BaseResp
import com.chunze.usercenter.data.api.UserApi
import com.chunze.usercenter.data.protocol.EditUserInfoReq
import com.chunze.usercenter.data.protocol.LoginReq
import com.chunze.usercenter.data.protocol.RegisterReq
import com.chunze.usercenter.data.protocol.UserInfo
import rx.Observable
import javax.inject.Inject

/**
 * Created by Administrator on 2018/2/26.
 */

class UserRepository @Inject constructor() {
    /**
     * 登录
     */
    fun login(userName: String, password: String, pushId: String)
            : Observable<BaseResp<UserInfo>> {

        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginReq(userName, password, pushId))
    }

    /**
     * 注册
     */
    fun register(userName: String, password: String, code: String): Observable<BaseResp<String>> {

        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(userName, password, code))
    }

    /**
     * 编辑个人资料
     */
    fun editUserInfo(userIcon: String, userName: String, gender: String, sign: String): Observable<BaseResp<UserInfo>> {

        return RetrofitFactory.instance.create(UserApi::class.java)
                .editUserInfo(EditUserInfoReq(userIcon, userName, gender, sign))

    }
}