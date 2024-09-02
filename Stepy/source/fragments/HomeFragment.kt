package com.steps.tracker.machine.analyzer.fragments

import DateUtils
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.apputility.Constant
import com.steps.tracker.machine.analyzer.apputility.formatNumbers
import com.steps.tracker.machine.analyzer.apputility.gone
import com.steps.tracker.machine.analyzer.apputility.visiable
import com.steps.tracker.machine.analyzer.base.BaseBindingFragment
import com.steps.tracker.machine.analyzer.databinding.FragmentHomeBinding
import com.steps.tracker.machine.analyzer.dialogs.PermissionRejectDialog
import com.steps.tracker.machine.analyzer.dialogs.PhysicalPermissionDialog
import com.steps.tracker.machine.analyzer.dialogs.RequirePermissionDialog
import com.steps.tracker.machine.analyzer.dialogs.StepGoalBottomDialog
import com.steps.tracker.machine.analyzer.dialogs.WhyWeNeedDialog
import com.steps.tracker.machine.analyzer.features.ui.splash.SplashActivity
import com.steps.tracker.machine.analyzer.interfaces.OnClickBottomSheetListener
import com.steps.tracker.machine.analyzer.permissions.PermissionUtils
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils
import com.steps.tracker.machine.analyzer.services.ResetStepForegroundService
import com.steps.tracker.machine.analyzer.services.StepServices

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {
    private var bottomSheetStepGoalDialog: StepGoalBottomDialog? = null
    private var permissionDialog: PhysicalPermissionDialog? = null
    private var permissionReject: PermissionRejectDialog? = null
    private var whyWeNeedDialog: WhyWeNeedDialog? = null
    private var requiredDialog: RequirePermissionDialog? = null
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                isGotoSettingActivity = false
                if (SharedPreferenceUtils.startStep) {
                    startStepService()
                }
            } else {
                isGotoSettingActivity = true
                showPermissionRejectDialog()
            }
        }


    lateinit var stepServiceIntent: Intent
    private var isGotoSettingActivity = false

    companion object {
        var currentStep = MutableLiveData<Int>()
    }

    override fun setBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        if (isGotoSettingActivity) {
            isGotoSettingActivity = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACTIVITY_RECOGNITION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    if (SharedPreferenceUtils.startStep) {
                        startStepService()
                    }
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (SharedPreferenceUtils.firstPermissionRequired) {
                            showRequirePermissionDialog()
                            SharedPreferenceUtils.firstPermissionRequired = false
                        }
                    }, 100)
                } else {
                    if (SharedPreferenceUtils.checkCountRejectPermission > 2)
                        showWhyWeNeedPermission()
                    else
                        showPermissionRejectDialog()
                }
            } else {
                checkPermission()
                if (SharedPreferenceUtils.checkCountRejectPermission > 2)
                    showWhyWeNeedPermission()
                else
                    showPermissionRejectDialog()
            }

        } else if (isGotoSettingActivity) {
            checkPermissionOnResume(Manifest.permission.ACTIVITY_RECOGNITION)
        } else {
            checkPermission()
        }
        super.onResume()

    }

    private fun checkPermissionOnResume(permission: String) {
        if (permission == Manifest.permission.ACTIVITY_RECOGNITION) {
            isGotoSettingActivity = false
            if (ActivityCompat.checkSelfPermission(
                    mContext,
                    permission
                ) == PackageManager.PERMISSION_DENIED
            ) {
                checkPermission()
                SharedPreferenceUtils.checkCountRejectPermission++
                if (SharedPreferenceUtils.checkCountRejectPermission > 2)
                    showWhyWeNeedPermission()
                else
                    showPermissionRejectDialog()
            }

        }

    }
    private fun checkPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACTIVITY_RECOGNITION
        )
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                if (SharedPreferenceUtils.firstPermissionRequired) {
                    showRequirePermissionDialog()
                    SharedPreferenceUtils.firstPermissionRequired = false
                }
            }
        }
    }


    override fun initView() {
        super.initView()
        binding.tvTarget.text = SharedPreferenceUtils.targetStep.toString()
        binding.tvContentHeader.text = SharedPreferenceUtils.yesterdayStep.toString()
        binding.tvStepRealTime.text = SharedPreferenceUtils.dayStep.toString()
        currentStep.observe(this@HomeFragment) {
            binding.tvStepRealTime.text = it.toString()
            updateUI()
        }
        setWelcomeMessage()
        updateUI()


    }

    private fun setWelcomeMessage() {
        val hour = DateUtils.getHour()
        when (hour) {
            in 5..12 -> binding.tvWellCome.text =
                ContextCompat.getString(mContext, R.string.good_morning)

            in 13..17 -> binding.tvWellCome.text =
                ContextCompat.getString(mContext, R.string.good_afternoon)

            else -> binding.tvWellCome.text =
                ContextCompat.getString(mContext, R.string.good_evening)

        }
    }

    private fun updateUI() {
        binding.chart.setArcValue(
            SharedPreferenceUtils.targetStep.toFloat(),
            SharedPreferenceUtils.dayStep.toFloat()
        )
        val stepCountKm =
            (SharedPreferenceUtils.dayStep * SharedPreferenceUtils.stepLength * Constant.CM_TO_KM)
        val stepCountKcal = SharedPreferenceUtils.dayStep * Constant.KcalOne
        val formattedNumberKcal = formatNumbers(stepCountKcal)
        val formattedNumberKm = formatNumbers(stepCountKm)
        val activeTime = DateUtils.convertSecondToTime(SharedPreferenceUtils.dayStep)

        binding.tvKmHome.text = formattedNumberKm
        binding.tvKcalHome.text = formattedNumberKcal
        binding.tvTimerMinHome.text = activeTime

        if (SharedPreferenceUtils.dayStep > 0) {
            binding.ivFire.visiable()
        }
        if ((SharedPreferenceUtils.dayStep.toFloat() / SharedPreferenceUtils.targetStep) * 100 < 25f) {
            binding.ivFire.setImageResource(R.drawable.ic_fire_01)
        } else if ((SharedPreferenceUtils.dayStep.toFloat() / SharedPreferenceUtils.targetStep) * 100 < 50f) {
            binding.ivFire.setImageResource(R.drawable.ic_fire_02)
        } else if ((SharedPreferenceUtils.dayStep.toFloat() / SharedPreferenceUtils.targetStep) * 100 < 75f) {
            binding.ivFire.setImageResource(R.drawable.ic_fire_03)
        } else {
            binding.ivFire.setImageResource(R.drawable.ic_fire_04)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initViewAction() {
        super.initViewAction()
        if (isAdded) {
            askForNotificationPermission()
        }

        if (SharedPreferenceUtils.setOrStartGoal)
            binding.lnGoal.gone()
        else
            binding.lnGoal.visiable()

        stepServiceIntent = Intent(mContext, StepServices::class.java)

        if (!SplashActivity.IS_PUSH) {
            SplashActivity.IS_PUSH = true
            pushData()
        }
        if (SharedPreferenceUtils.startStep) {
            startStepService()
            binding.ivStepStart.setImageResource(R.drawable.ic_pause_step_home)
            binding.tvPause.gone()
            binding.lnTarget.visiable()
        } else {
            stopStepService()
            binding.ivStepStart.setImageResource(R.drawable.ic_step_pause)
            binding.tvPause.visiable()
            binding.lnTarget.gone()
        }
    }

    private fun pushData() {
        var intent = Intent(requireActivity(), ResetStepForegroundService::class.java)
        mContext.startService(intent)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askForNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                mContext,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            showPhysicalPermissionDialog()

        } else {
            if (SharedPreferenceUtils.startStep) {
                Handler(Looper.getMainLooper()).postDelayed({
                    startStepService()
                }, 1000)
            }
        }
    }

    private fun showPhysicalPermissionDialog() {
        if (permissionDialog == null) {
            permissionDialog = PhysicalPermissionDialog(mContext) {
                PermissionUtils.requestPermission(
                    Manifest.permission.ACTIVITY_RECOGNITION,
                    requestPermissionLauncher
                )
                isGotoSettingActivity = true
                SharedPreferenceUtils.checkCountRejectPermission++
            }
        }
        if (SharedPreferenceUtils.checkCountRejectPermission > 1)
            showPermissionRejectDialog()
        else if (!permissionDialog!!.isShowing && SharedPreferenceUtils.firstPermissionRequired) {
            permissionDialog!!.show()
        }
    }

    private fun showPermissionRejectDialog() {
        if (permissionDialog != null)
            permissionDialog!!.hide()

        if (permissionReject == null) {
            permissionReject = PermissionRejectDialog(mContext) {
                PermissionUtils.requestPermission(
                    Manifest.permission.ACTIVITY_RECOGNITION,
                    requestPermissionLauncher
                )
            }
            isGotoSettingActivity = true
            SharedPreferenceUtils.checkCountRejectPermission++
        }
        if (!permissionReject!!.isShowing) {
            permissionReject!!.show()
        }
    }

    private fun showWhyWeNeedPermission() {
        if (permissionDialog != null)
            permissionDialog!!.hide()
        if (permissionReject != null)
            permissionReject!!.hide()

        if (whyWeNeedDialog == null) {
            whyWeNeedDialog = WhyWeNeedDialog(mContext) {
                isGotoSettingActivity = true
                gotoSetting()
            }
        }
        if (whyWeNeedDialog!!.isShowing) {
            whyWeNeedDialog!!.show()
        }
    }

    private fun showRequirePermissionDialog() {
        if (permissionDialog != null)
            permissionDialog!!.hide()
        if (permissionReject != null)
            permissionReject!!.hide()

        if (whyWeNeedDialog != null)
            whyWeNeedDialog!!.hide()

        if (requiredDialog == null) {
            requiredDialog = RequirePermissionDialog(
                requireContext(),
            ) {
                val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                intent.data = Uri.parse("package:${mContext.packageName}")
                startActivity(intent)
            }
        }
        if (!requiredDialog!!.isShowing) {
            requiredDialog!!.show()
        }
    }

    private fun gotoSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", mContext.packageName, null)
        startActivity(intent)

    }

    private fun startStepService() {
        requireContext().startService(stepServiceIntent)
    }

    private fun stopStepService() {
        requireContext().stopService(stepServiceIntent)
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.ivStepStart, binding.btnStepGoal, binding.ivClose)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.ivStepStart -> {
                if (!SharedPreferenceUtils.startStep) {
                    SharedPreferenceUtils.startStep = true
                    startStepService()
                    binding.ivStepStart.setImageResource(R.drawable.ic_pause_step_home)
                    binding.tvPause.gone()
                    binding.lnTarget.visiable()
                } else {
                    stopStepService()
                    SharedPreferenceUtils.startStep = false
                    binding.ivStepStart.setImageResource(R.drawable.ic_step_pause)
                    binding.tvPause.visiable()
                    binding.lnTarget.gone()
                }

            }

            binding.btnStepGoal -> {
                openStepGoalBottomSheet()
                binding.lnGoal.gone()
                SharedPreferenceUtils.setOrStartGoal = true
            }

            binding.ivClose -> {
                binding.lnGoal.gone()
                SharedPreferenceUtils.setOrStartGoal = true

            }
        }
    }

    private fun openStepGoalBottomSheet() {
        if (bottomSheetStepGoalDialog == null) {
            bottomSheetStepGoalDialog = StepGoalBottomDialog(mContext,
                object : OnClickBottomSheetListener {
                    override fun onClickSaveFrom() {
                        binding.btnStepGoal.text = SharedPreferenceUtils.targetStep.toString()
                    }
                })
        }
        bottomSheetStepGoalDialog!!.checkShowBottomSheet()

    }
}