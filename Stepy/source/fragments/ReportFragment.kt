package com.steps.tracker.machine.analyzer.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steps.tracker.machine.analyzer.base.BaseBindingFragment
import com.steps.tracker.machine.analyzer.databinding.FragmentReportBinding

class ReportFragment : BaseBindingFragment<FragmentReportBinding>() {
    override fun setBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportBinding {
        return FragmentReportBinding.inflate(layoutInflater)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}