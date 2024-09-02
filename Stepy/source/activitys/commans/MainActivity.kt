package com.steps.tracker.machine.analyzer.activitys.commans

import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.apputility.NotificationMangers
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivityMainBinding
import com.steps.tracker.machine.analyzer.fragments.HealthFragment
import com.steps.tracker.machine.analyzer.fragments.HomeFragment
import com.steps.tracker.machine.analyzer.fragments.RankFragment
import com.steps.tracker.machine.analyzer.fragments.ReportFragment
import com.steps.tracker.machine.analyzer.fragments.SettingFragment
import java.util.Calendar

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    private val HOME_FRAGMENT = HomeFragment()
    private val REPORT_FRAGMENT = ReportFragment()
    private val RANK_FRAGMENT = RankFragment()
    private val HEALTH_FRAGMENT = HealthFragment()
    private val SETTING_FRAGMENT = SettingFragment()
    private val CurrentFragment: Fragment = HOME_FRAGMENT

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@MainActivity
    }

    override fun onResume() {
        super.onResume()
        NotificationMangers.showNotificationStepGoal(this)
    }

    override fun initView() {
        super.initView()
        initBottomNavigation()
    }

    override fun initViewAction() {
        super.initViewAction()
        binding.bottomNav.selectedItemId = R.id.action_home
        binding.bottomNav.setOnItemSelectedListener {bottomMenu ->
            when (bottomMenu.itemId) {
                R.id.action_home -> {
                        loadFragment(HOME_FRAGMENT, CurrentFragment)

                }

                R.id.action_report -> {
                        loadFragment(REPORT_FRAGMENT, CurrentFragment)

                }

                R.id.action_health -> {
                        loadFragment(HEALTH_FRAGMENT, CurrentFragment)

                }

                R.id.action_rank -> {
                        loadFragment(RANK_FRAGMENT, CurrentFragment)

                }

                R.id.action_setting -> {
                        loadFragment(SETTING_FRAGMENT, CurrentFragment)

                }


            }
            true
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNav.setOnApplyWindowInsetsListener(null)
        supportFragmentManager.beginTransaction()
//            .add(binding.layoutAddFragmentMain.id, REPORT_FRAGMENT)
            .add(binding.layoutAddFragmentMain.id, HOME_FRAGMENT)
            .show(HOME_FRAGMENT).commit()
    }

    private fun loadFragment(showFragment: Fragment, hideFragment: Fragment) {
        Log.e(TAG, "loadFragment: Show Fragment --->"+showFragment.javaClass.simpleName+" Current Fragment---> "+hideFragment.javaClass.simpleName, )
        supportFragmentManager.beginTransaction().replace(binding.layoutAddFragmentMain.id,showFragment).commit()
//        if (hideFragment == REPORT_FRAGMENT)
//            supportFragmentManager.beginTransaction().hide(hideFragment).commitAllowingStateLoss()
//        else supportFragmentManager.beginTransaction().remove(hideFragment).commitAllowingStateLoss()
//
//        if (showFragment != REPORT_FRAGMENT) supportFragmentManager.beginTransaction().add(binding.layoutAddFragmentMain.id, showFragment).commitAllowingStateLoss()
//         else supportFragmentManager.beginTransaction().show(showFragment).commitAllowingStateLoss()

    }


    override fun initViewListener() {
        super.initViewListener()
    }



    override fun onClick(v: View?) {
        when (v) {

        }
    }

    override fun onStop() {
        super.onStop()
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        NotificationMangers.showNotificationInfoSteps(this, hour, minute)
    }
}