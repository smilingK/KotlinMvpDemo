package com.chunze.kotlinmvpdemo.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.chunze.baselibrary.ui.activity.BaseActivity
import com.chunze.kotlinmvpdemo.R
import com.chunze.kotlinmvpdemo.ui.fragment.HomeFragment
import com.chunze.kotlinmvpdemo.ui.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    //Fragment 栈管理
    private val mFragmentStack = Stack<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { HomeFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mNotificationsFragment by lazy { HomeFragment() }
    private val mMyFragment by lazy { MyFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNavBar()
        switchoverFragment(0)

    }

    private fun initFragment() {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.add(R.id.mContainer, mHomeFragment)
        fragmentManager.add(R.id.mContainer, mCategoryFragment)
        fragmentManager.add(R.id.mContainer, mCartFragment)
        fragmentManager.add(R.id.mContainer, mNotificationsFragment)
        fragmentManager.add(R.id.mContainer, mMyFragment)
        fragmentManager.commit()
        mFragmentStack.add(mHomeFragment)
        mFragmentStack.add(mCategoryFragment)
        mFragmentStack.add(mCartFragment)
        mFragmentStack.add(mNotificationsFragment)
        mFragmentStack.add(mMyFragment)

    }

    private fun initBottomNavBar() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                switchoverFragment(position)
            }
        })
    }

    private fun switchoverFragment(position: Int) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        mFragmentStack.forEach {
            fragmentManager.hide(it)
        }

        fragmentManager.show(mFragmentStack[position])
        fragmentManager.commit()
    }


}
