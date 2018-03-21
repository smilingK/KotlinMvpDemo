package com.chunze.usercenter.data.api

import com.chunze.baselibrary.data.protocol.BaseResp
import com.chunze.usercenter.data.protocol.EditUserInfoReq
import com.chunze.usercenter.data.protocol.LoginReq
import com.chunze.usercenter.data.protocol.RegisterReq
import com.chunze.usercenter.data.protocol.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Administrator on 2018/2/26.
 */
interface UserApi {
    /**
     * 登录
     */
    @POST("userCenter/login")
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>

    /**
     * 注册
     */
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>

    /**
     * 编辑个人资料
     */
    @POST("userCenter/editUser")
    fun editUserInfo(@Body req: EditUserInfoReq): Observable<BaseResp<UserInfo>>

}