package com.chunze.baselibrary.ui.activity

import android.os.Bundle
import com.chunze.baselibrary.common.BaseApplication
import com.chunze.baselibrary.injection.component.ActivityComponent
import com.chunze.baselibrary.injection.component.DaggerActivityComponent
import com.chunze.baselibrary.injection.module.ActivityModule
import com.chunze.baselibrary.injection.module.LifecycleProviderModule
import com.chunze.baselibrary.presenter.BasePresenter
import com.chunze.baselibrary.presenter.view.BaseView
import com.chunze.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by Administrator on 2018/2/24.
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T
    lateinit var activityComponent: ActivityComponent

    private lateinit var loadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        initInjection()
        loadingDialog = ProgressLoading.create(this)
    }

    private fun initActivityInjection() {

        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }

    override fun showLoading() {
        loadingDialog.showLoading()
    }

    override fun hideLoading() {
        loadingDialog.hideLoading()
    }

    override fun showError(msg: String) {
        toast(msg)
        loadingDialog.hideLoading()
    }

    abstract fun initInjection()


}