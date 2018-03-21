package com.chunze.provider.arouter

/**
 * Created by Administrator on 2018/3/13.
 */
object RouterPath {
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    class MessageCenter {
        companion object {
            const val PATH_MSG = "/messageCenter/msg"
        }
    }

    class MainCenter {
        companion object {
            const val PATH_SETTING = "/mainCenter/setting"
        }
    }
}