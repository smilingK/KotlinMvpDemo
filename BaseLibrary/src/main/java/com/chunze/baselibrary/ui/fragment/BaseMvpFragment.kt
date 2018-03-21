package com.chunze.baselibrary.ui.fragment

import android.os.Bundle
import com.chunze.baselibrary.common.BaseApplication
import com.chunze.baselibrary.injection.component.ActivityComponent
import com.chunze.baselibrary.injection.component.DaggerActivityComponent
import com.chunze.baselibrary.injection.module.ActivityModule
import com.chunze.baselibrary.injection.module.LifecycleProviderModule
import com.chunze.baselibrary.presenter.BasePresenter
import com.chunze.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/2/27.
 */
abstract class BaseMvpFragment <T : BasePresenter<*>>:BaseFragment(),BaseView {

    @Inject
    lateinit var mPresenter: T
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        initInjection()
    }

    private fun initActivityInjection() {

        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    abstract fun initInjection()

}