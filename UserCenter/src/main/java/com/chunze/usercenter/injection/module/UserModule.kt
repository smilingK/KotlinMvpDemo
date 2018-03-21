package com.chunze.usercenter.injection.module

import com.chunze.usercenter.service.UserService
import com.chunze.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Administrator on 2018/2/27.
 */
@Module
class UserModule {
    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}