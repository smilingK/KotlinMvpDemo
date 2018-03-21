package com.chunze.usercenter.service

import com.chunze.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * Created by Administrator on 2018/2/26.
 */
interface UserService {
    fun login(userName: String, password: String, pushId: String): Observable<UserInfo>
    fun register(userName: String, password: String, code: String): Observable<Boolean>
    fun editUserInfo(userIcon: String, userName: String, gender: String, sign: String): Observable<UserInfo>
}