package com.chunze.baselibrary.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.chunze.baselibrary.R
import org.jetbrains.anko.find


/**
 * Created by Administrator on 2018/2/28.
 */
class ProgressLoading private constructor(content: Context, theme: Int) : Dialog(content, theme) {

    companion object {
        private var animationDrawable: AnimationDrawable? = null
        private lateinit var loadingDialog: ProgressLoading

        fun create(content: Context): ProgressLoading {
            //设置布局
            loadingDialog = ProgressLoading(content, R.style.LightProgressDialog)
            loadingDialog.setContentView(R.layout.progress_dialog)

            //设置属性
            loadingDialog.setCancelable(true)
            loadingDialog.setCanceledOnTouchOutside(false)

            val lp = loadingDialog.window.attributes
            lp.gravity = Gravity.CENTER
            lp.dimAmount = 0.2f
            loadingDialog.window.attributes = lp

            //获取视图
            animationDrawable = loadingDialog.find<ImageView>(R.id.iv_loading).background as AnimationDrawable

            return loadingDialog
        }
    }

    fun showLoading() {
        super.show()
        animationDrawable?.start()
    }

    fun hideLoading() {
        super.dismiss()
        animationDrawable?.stop()
    }


}