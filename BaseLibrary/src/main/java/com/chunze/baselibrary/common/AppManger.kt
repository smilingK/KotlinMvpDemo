package com.chunze.baselibrary.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by Administrator on 2018/2/27.
 */
class AppManger private constructor() {

    private val acticityStack: Stack<Activity> = Stack()

    companion object {
        val instance: AppManger by lazy { AppManger() }
    }

    /**
     * 入栈
     */
    fun addActivity(activity: Activity) {
        acticityStack.add(activity)
    }

    /**
     * 出栈
     */
    fun finishActivity(activity: Activity) {

        activity.finish()
        acticityStack.remove(activity)
    }

    /**
     * 获取当前栈
     */
    fun currentActivity(): Activity {
        return acticityStack.lastElement()
    }

    /**
     * 清理栈
     */
    fun finishAllActivity() {
        for (activity in acticityStack) {
            activity.finish()
        }
        acticityStack.clear()
    }


    fun exitAPP(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)

    }
}