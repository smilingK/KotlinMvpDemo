package com.chunze.baselibrary.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.chunze.baselibrary.R
import com.jakewharton.rxbinding.view.RxView
import rx.Observer
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2018/2/28.
 */
object GlideUtils {
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).centerCrop().into(imageView)
    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).fitCenter().into(imageView)
    }

    /*
        当fragment或者activity失去焦点或者destroyed的时候，Glide会自动停止加载相关资源，确保资源不会被浪费
     */
    fun loadUrlImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).placeholder(R.drawable.default_shuff_pic).error(R.drawable.default_shuff_pic).centerCrop().into(
                object : SimpleTarget<GlideDrawable>() {
                    override fun onResourceReady(resource: GlideDrawable,
                                                 glideAnimation: GlideAnimation<in GlideDrawable>) {
                        imageView.setImageDrawable(resource)
                    }
                })
    }


    /*
        扩展点击事件，参数为方法
     */
    fun onClickA(view: View, method: () -> Unit) {

        RxView.clicks(view).throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(object : Observer<Any> {
                    override fun onError(e: Throwable?) {
                    }

                    override fun onNext(t: Any) {
                        method()
                    }

                    override fun onCompleted() {
                    }
                })
    }
}