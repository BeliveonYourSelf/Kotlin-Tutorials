package com.steps.tracker.machine.analyzer.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steps.tracker.machine.analyzer.base.BaseBindingFragment
import com.steps.tracker.machine.analyzer.databinding.FragmentHealthBinding
import com.steps.tracker.machine.analyzer.databinding.FragmentReportBinding

class HealthFragment : BaseBindingFragment<FragmentHealthBinding>() {
    override fun setBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHealthBinding {
        return FragmentHealthBinding.inflate(layoutInflater)
    }

    override fun onClick(v: View?) {

    }
}