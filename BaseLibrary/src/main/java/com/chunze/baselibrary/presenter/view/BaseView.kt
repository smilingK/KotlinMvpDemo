package com.chunze.baselibrary.presenter.view

/**
 * Created by Administrator on 2018/2/24.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)
}