package com.steps.tracker.machine.analyzer.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steps.tracker.machine.analyzer.base.BaseBindingFragment
import com.steps.tracker.machine.analyzer.databinding.FragmentHealthBinding
import com.steps.tracker.machine.analyzer.databinding.FragmentReportBinding
import com.steps.tracker.machine.analyzer.databinding.FragmentSettingsBinding

class SettingFragment : BaseBindingFragment<FragmentSettingsBinding>() {
    override fun setBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun onClick(v: View?) {

    }
}