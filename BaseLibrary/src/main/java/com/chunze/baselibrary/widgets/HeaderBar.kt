package com.chunze.baselibrary.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.chunze.baselibrary.R
import com.chunze.baselibrary.ext.onClick
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 * Created by Administrator on 2018/2/28.
 */
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var isShowBack = true
    private var titleText: String? = null
    private var rightText: String? = null

    init {
        val headerBarAttrs = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)

        isShowBack = headerBarAttrs.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = headerBarAttrs.getString(R.styleable.HeaderBar_titleText)
        rightText = headerBarAttrs.getString(R.styleable.HeaderBar_rightText)
        headerBarAttrs.recycle()
        initView()
    }

    private fun initView() {

        View.inflate(context, R.layout.layout_header_bar, this)

        if (isShowBack) {
            mBackIv.visibility = View.VISIBLE
        } else {
            mBackIv.visibility = View.GONE
        }

        titleText?.let {
            mTitleTv.text = it
            mTitleTv.visibility = View.VISIBLE
        }

        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }

        mBackIv.onClick {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }

    fun getBackIv(): ImageView {
        return mBackIv
    }

    fun getTitleTv(): TextView {
        return mTitleTv
    }

    fun getRightTv(): TextView {
        return mRightTv
    }

}