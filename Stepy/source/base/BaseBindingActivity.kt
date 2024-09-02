@file:Suppress("unused")

package com.steps.tracker.machine.analyzer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.UiThread
import androidx.room.Room
import androidx.viewbinding.ViewBinding
import com.steps.tracker.machine.analyzer.apputility.Constant
import com.steps.tracker.machine.analyzer.databases.AppDatabase

abstract class BaseBindingActivity<VB : ViewBinding> : BaseActivity() {

    // your activity binding object
    lateinit var binding: VB
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        setParamBeforeLayoutInit()
        super.onCreate(null)
        database = Room.databaseBuilder(this,AppDatabase::class.java,Constant.STEP_DB)
            .allowMainThreadQueries()
            .build()
        setContentView(getInflatedLayout(layoutInflater))
    }

    override fun getLayoutRes(): Int? {
        return null
    }

    /**
     * internal functions for set layout using Binding object
     *
     * @param inflater your activity layoutInflater
     * @return this will return your binding view
     */
    @UiThread
    private fun getInflatedLayout(inflater: LayoutInflater): View {
        this.binding = setBinding(inflater)
        return this.binding.root
    }

    /**
     * For init your binding object
     *
     * @param layoutInflater your activity layoutInflater
     * @return Binding property
     * in Java :- {return ActivityMainBinding.inflate(layoutInflater);}
     * in Kotlin :- {return ActivityMainBinding.inflate(layoutInflater)}
     */
    @UiThread
    abstract fun setBinding(layoutInflater: LayoutInflater): VB
}