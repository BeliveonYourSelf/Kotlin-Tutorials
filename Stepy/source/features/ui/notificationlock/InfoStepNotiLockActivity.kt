package com.steps.tracker.machine.analyzer.features.ui.notificationlock

import DateUtils.generateWeeksInYear
import DateUtils.getCurrentYear
import DateUtils.getDayOfYear
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivityInfoStepNotiLockBinding
import com.steps.tracker.machine.analyzer.features.ui.splash.SplashActivity
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils
import java.util.Calendar

class InfoStepNotiLockActivity : BaseBindingActivity<ActivityInfoStepNotiLockBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityInfoStepNotiLockBinding {
        return  ActivityInfoStepNotiLockBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@InfoStepNotiLockActivity
    }

    override fun initView() {
        super.initView()
        binding.txtDay.text=SharedPreferenceUtils.dayStep.toString()
        binding.txtWeek.text=calculatorStepWeek()
        binding.txtMonth.text=calculateTotalStepOfMonth()
    }

    override fun initViewAction() {
        super.initViewAction()
        calculatorStepWeek()
        calculateTotalStepOfMonth()
        showOnLockscreen()
        dismissKeyguard()
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.btnAdd)
    }


    override fun onClick(v: View?) {
        when(v)
        {
            binding.btnAdd ->{
                    launchActivity(getActivityIntent<SplashActivity> {  })
            }

        }
    }
    private fun calculatorStepWeek(): String{
        val currentWeek: MutableList<Int> = mutableListOf()
        val dayOfYear = getDayOfYear()
        currentWeek.clear()
        getCurrentWeekInList(dayOfYear)?.let { currentWeek.addAll(ArrayList(it)) }
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR,currentWeek.first())
        val startOfWeek = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR,7)
        val endOfWeek = calendar.timeInMillis
        return database.stepDao().getStepsDay(DateUtils.getStartOfDay(startOfWeek), DateUtils.getEndOfDay(endOfWeek)).toString()
    }
    private fun getCurrentWeekInList(dayOfYear: Int): MutableList<Int>? {
        val listWeek: MutableList<MutableList<Int>> = mutableListOf()
        listWeek.addAll(generateWeeksInYear(getCurrentYear())) // list các ngày tuần hiện tại
        for (index in 0 until listWeek.size) {
            val item = listWeek[index]
            if (item[0] <= dayOfYear && item[item.size - 1] >= dayOfYear) {
                return item
            }
        }
        return null
    }
    private fun calculateTotalStepOfMonth(): String{
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH,1)
        val startOfMonth = calendar.timeInMillis
        calendar.add(Calendar.MONTH,1)
        calendar.add(Calendar.DAY_OF_YEAR,-1)
        val endOfMonth = calendar.timeInMillis
        return database.stepDao().getStepsDay(DateUtils.getStartOfDay(startOfMonth), DateUtils.getEndOfDay(endOfMonth)).toString()
    }

}