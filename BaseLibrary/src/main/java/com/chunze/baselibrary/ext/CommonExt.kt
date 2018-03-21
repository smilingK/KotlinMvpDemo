package com.chunze.baselibrary.ext

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.chunze.baselibrary.data.protocol.BaseResp
import com.chunze.baselibrary.rx.BaseFunc
import com.chunze.baselibrary.rx.BaseFuncBoolean
import com.chunze.baselibrary.rx.BaseSubscriber
import com.chunze.baselibrary.utils.GlideUtils
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2018/2/26.
 */

//var currentTime: Long = 0

/*
    扩展Observable执行
 */
fun <T> Observable<T>.execute(lifecycleProvider: LifecycleProvider<*>,subscriber: BaseSubscriber<T>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}


/*
    扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

/*
    扩展Boolean类型数据转换
 */
fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

/*
    防止重复点击
 */
fun <T> Observable<T>.onPerfectClick(method: () -> Unit) {

    this.throttleFirst(3, TimeUnit.SECONDS)
            .subscribe(object : Observer<T> {

                override fun onNext(t: T) {
                    method()
                }

                override fun onError(e: Throwable?) {}
                override fun onCompleted() {}
            })
}

/*
    扩展点击事件
 */
fun View.onClick(listener: View.OnClickListener): View {
    setOnClickListener(listener)
    return this
}

/*
    扩展点击事件，参数为方法
 */
fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method() }
    return this
}

fun Button.enabledDetection(editText: EditText, method: () -> Boolean) {
    val btn = this
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btn.isEnabled = method()
        }
    })
}
/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
