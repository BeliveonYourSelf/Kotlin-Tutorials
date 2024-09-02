@file:Suppress("unused")

package com.universal.finance.tools.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.viewbinding.ViewBinding

/**
 * @author Akshay Harsoda
 * @since 18 Mar 2021
 *
 * BaseBindingFragment.kt - A simple class contains some modifications to the native Fragment.
 * This Class use with ViewBinding property.
 * also you have use this class in JAVA or KOTLIN both language.
 *
 * use of this class
 * you have to extend your Activity using this class like.
 * in Java :- {class MainFragment extends BaseBindingFragment<FragmentMainBinding>}
 * in Kotlin :- {class MainFragment : BaseBindingFragment<FragmentMainBinding>()}
 *
 * @property VB this is your layout file binding object
 *
 * for more details {@see BaseFragment.kt}.
 */
abstract class BaseBindingFragment<VB : ViewBinding> : BaseFragment() {

    // your fragment binding object
    lateinit var binding: VB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return getInflatedLayout(inflater, container)
    }

    override fun getLayoutRes(): Int? {
        return null
    }

    /**
     * internal functions for set layout using Binding object
     *
     * @param inflater your activity layoutInflater
     * @param container view to be the parent of the generated hierarchy
     * @return this will return your binding view
     */
    @UiThread
    private fun getInflatedLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        this.binding = setBinding(inflater, container)
        return this.binding.root
    }

    /**
     * For init your binding object
     *
     * @param layoutInflater your activity layoutInflater
     * @param container view to be the parent of the generated hierarchy
     * @return Binding property
     * in Java :- {return FragmentMainBinding.inflate(layoutInflater, container, false);}
     * in Kotlin :- {return FragmentMainBinding.inflate(layoutInflater, container, false)}
     */
    @UiThread
    abstract fun setBinding(layoutInflater: LayoutInflater, container: ViewGroup?): VB

    /**
     * This Method Call when user will visible for your app
     */
    open fun isUserVisible(visible: Boolean) {

    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (this::binding.isInitialized) {
            isUserVisible(menuVisible)
        }
    }


}