package com.chunze.baselibrary.widgets

import android.content.Context
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.Button
import com.chunze.baselibrary.R

/**
 * Created by Administrator on 2018/2/28.
 */
/*
    获取验证码按钮
    带倒计时
 */
class VerifyButton(context: Context, attrs: AttributeSet) : Button(context, attrs) {
    private val mHandler: Handler
    private var mCount = 60
    private var mOnVerifyBtnClick: OnVerifyBtnClick? = null
    private val mContext = context
    private val mBgColor: Int
    private val mClickColor: Int


    init {
        this.text = "获取验证码"
        mHandler = Handler()
        val verifyBtnAttrs = context.obtainStyledAttributes(attrs, R.styleable.VerifyButton)
        mBgColor = verifyBtnAttrs.getColor(R.styleable.VerifyButton_bgColor, ContextCompat.getColor(mContext, R.color.common_disable))
        mClickColor = verifyBtnAttrs.getColor(R.styleable.VerifyButton_clickColor, ContextCompat.getColor(mContext, R.color.common_disable))
        this@VerifyButton.setBackgroundColor(mBgColor)

        verifyBtnAttrs.recycle()
    }

    /*
        倒计时，并处理点击事件
     */
    fun requestSendVerifyNumber() {
        mHandler.postDelayed(countDown, 0)
        if (mOnVerifyBtnClick != null) {
            mOnVerifyBtnClick!!.onClick()
        }

    }

    /*
        倒计时
     */
    private val countDown = object : Runnable {
        override fun run() {

            val count = mCount.toString() + " s"
            this@VerifyButton.text = count
//            this@VerifyButton.setBackgroundColor(ContextCompat.getColor(mContext, R.color.common_disable))
            this@VerifyButton.setBackgroundColor(mClickColor)
            this@VerifyButton.setTextColor(ContextCompat.getColor(mContext, R.color.common_white))
            this@VerifyButton.isEnabled = false

            if (mCount > 0) {
                mHandler.postDelayed(this, 1000)
            } else {
                resetCounter()
            }
            mCount--
        }
    }

    fun removeRunable() {
        mHandler.removeCallbacks(countDown)
    }

    /*
        恢复到初始状态
     */
    fun resetCounter(vararg text: String) {
        this.isEnabled = true
        if (text.isNotEmpty() && "" != text[0]) {
            this.text = text[0]
        } else {
            this.text = "重获验证码"
        }
        this.setBackgroundColor(mBgColor)
        this.setTextColor(ContextCompat.getColor(context, R.color.common_white))
        mCount = 60
    }

    /*
        点击事件接口
     */
    interface OnVerifyBtnClick {
        fun onClick()
    }

    fun setOnVerifyBtnClick(onVerifyBtnClick: OnVerifyBtnClick) {
        this.mOnVerifyBtnClick = onVerifyBtnClick
    }
}