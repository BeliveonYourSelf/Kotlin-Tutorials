package com.steps.tracker.machine.analyzer.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steps.tracker.machine.analyzer.base.BaseBindingFragment
import com.steps.tracker.machine.analyzer.databinding.FragmentRankBinding
import com.steps.tracker.machine.analyzer.databinding.FragmentReportBinding

class RankFragment : BaseBindingFragment<FragmentRankBinding>() {
    override fun setBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRankBinding {
        return FragmentRankBinding.inflate(layoutInflater)
    }

    override fun onClick(v: View?) {

    }
}