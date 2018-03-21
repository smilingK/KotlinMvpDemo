package com.chunze.messagecenter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cn.jpush.android.api.JPushInterface

/**
 * Created by Administrator on 2018/3/13.
 */
/*
    自定义Push 接收器
 */
class MessageReceiver : BroadcastReceiver() {
    private val TAG = "MessageReceiver"
    override fun onReceive(context: Context, intent: Intent) {

        val bundle = intent.extras
        Log.d(TAG, "onReceive - " + intent.action + ", extras: " + bundle)

        when {
            JPushInterface.ACTION_REGISTRATION_ID == intent.action -> Log.d(TAG, "JPush用户注册成功")
            JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action -> {
                Log.d(TAG, "接受到推送下来的自定义消息")

            }
            JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action -> Log.d(TAG, "接受到推送下来的通知")
            JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action -> {
                Log.d(TAG, "用户点击打开了通知")


            }
            else -> Log.d(TAG, "Unhandled intent - " + intent.action)
        }
    }
}