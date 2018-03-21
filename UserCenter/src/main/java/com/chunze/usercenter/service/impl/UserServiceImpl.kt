package com.chunze.usercenter.service.impl

import com.chunze.baselibrary.ext.convert
import com.chunze.baselibrary.ext.convertBoolean
import com.chunze.usercenter.data.protocol.UserInfo
import com.chunze.usercenter.data.repository.UserRepository
import com.chunze.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Created by Administrator on 2018/2/26.
 */
class UserServiceImpl @Inject constructor() : UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun login(userName: String, password: String, pushId: String)
            : Observable<UserInfo> {
        return repository.login(userName, password, pushId).convert()
    }

    override fun register(userName: String, password: String, code: String)
            : Observable<Boolean> {
        return repository.register(userName, password, code).convertBoolean()
    }

    override fun editUserInfo(userIcon: String, userName: String, gender: String, sign: String)
            : Observable<UserInfo> {
        return repository.editUserInfo(userIcon, userName, gender, sign).convert()
    }

}

