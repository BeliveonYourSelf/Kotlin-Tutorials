@file:Suppress("unused")

package com.steps.tracker.machine.analyzer.base

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * @author Akshay Harsoda
 * @since 18 Mar 2021
 *
 * BaseActivity.kt - A simple class contains some modifications to the native Activity.
 * This Class use with or without ViewBinding property.
 * also you have use this class in JAVA or KOTLIN both language.
 *
 * use of this class
 * you have to extend your Activity using this class like.
 * in Java :- {class MainActivity extends BaseActivity}
 * in Kotlin :- {class MainActivity : BaseActivity()}
 *
 * NOTE :- if you use this class with ViewBinding then you must override onCreate() method of Activity
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope, View.OnClickListener {

    // for log
    val TAG: String = javaClass.simpleName

    var mJob: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    // your activity context object
    val mActivity: AppCompatActivity
        get() {
            return getActivityContext()
        }

    private var mRequestCode: Int = 0

    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            fromActivityResult(
                requestCode = mRequestCode,
                resultCode = result.resultCode,
                data = result.data
            )
        }

    public override fun onCreate(savedInstanceState: Bundle?) {
        setParamBeforeLayoutInit()
        super.onCreate(null)
        getLayoutRes()?.let {
            setContentView(it)
        }
    }

    override fun onResume() {
        super.onResume()
        initJob()

    }

    /**
     * If You Not Using viewBinding
     * @param layoutResID Pass Your Layout File Resource Id Like This 'R.layout.activity_main'
     */
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initJob()
        initView()
        initViewAction()
        initViewListener()
    }

    /**
     * If You Using viewBinding
     * @param view pass Your Layout File Like 'ActivityMainBinding.inflate(getLayoutInflater()).getRoot()'
     */
    override fun setContentView(view: View?) {
        super.setContentView(view)
        initJob()
        initView()
        initViewAction()
        initViewListener()

    }

    /**
     * This method for initialized your Coroutine job
     */
    private fun initJob() {
        mJob = Job()
    }

    @UiThread
    @LayoutRes
    abstract fun getLayoutRes(): Int?

    @UiThread
    abstract fun getActivityContext(): AppCompatActivity

    /**
     * This method for set-up your data before call setContentView()
     */
    open fun setParamBeforeLayoutInit() {}

    /**
     * This method For Init All Ads.
     */
    @UiThread
    open fun initAds() {
    }

    /**
     * This method For Init Your Layout File's All View
     */
    @UiThread
    open fun initView() {
    }

    /**
     * This method For Init Your Default Action Performance On View
     */
    @UiThread
    open fun initViewAction() {
    }

    /**
     * This method For Set Your All Type Of Listeners
     */
    @UiThread
    open fun initViewListener() {
    }

    /**
     * This method For Set Your All View Click Listener,
     * now you no need to write multiple line code for 'View.setOnClickListener(this)'
     * just call this method and pass your all view like
     *
     * setClickListener(view1, view2)
     *
     * @param fViews list of your all passed view's.
     */
    @UiThread
    open fun setClickListener(vararg fViews: View) {
        for (lView in fViews) {
            lView.setOnClickListener(this)
        }
    }

    /**
     * This method For Update Fragment & Attach In FrameLayout
     *
     * you have call this method using FrameLayout.
     * @param fFragment your fragment
     * @param enterAnim your fragment enter animation file
     * @param exitAnim your fragment exit animation file
     */
    open fun FrameLayout.updateFragment(
        fFragment: Fragment,
        @AnimatorRes @AnimRes enterAnim: Int = android.R.anim.fade_in,
        @AnimatorRes @AnimRes exitAnim: Int = android.R.anim.fade_out,
    ) {

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(enterAnim, exitAnim)
            .replace(this.id, fFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    /**
     * This Method for get your next activity intent
     *
     * @param fNextActivityClass Your Next Activity
     * @param isAddFlag [Default value:- true] for set up your activity flag in your intent
     * @param fBundle lambda fun for pass data throw intent
     */
    inline fun <reified T : Activity> getActivityIntent(
//        fNextActivityClass: Class<out T>,
        isAddFlag: Boolean = true,
        fBundle: Bundle.() -> Unit = {},
    ): Intent {
//        val lIntent = Intent(mActivity, fNextActivityClass)
        val lIntent = Intent(mActivity, T::class.java)

        if (isAddFlag) {
            lIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            lIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        lIntent.putExtras(Bundle().apply(fBundle))

        return lIntent
    }

    /**
     * This Method will replace your default method of [startActivityForResult]
     *
     * @param fIntent Your Launcher Screen Intent
     * @param fRequestCode Your Request Code For Get Result Of Your Next Activity
     * @param fEnterAnimId your activity Enter animation
     * @param fExitAnimId your activity Exit animation
     */
    open fun launchActivityForResult(
        fIntent: Intent,
        fRequestCode: Int,
        @AnimatorRes @AnimRes fEnterAnimId: Int = android.R.anim.fade_in,
        @AnimatorRes @AnimRes fExitAnimId: Int = android.R.anim.fade_out
    ) {
        mRequestCode = fRequestCode
        launcher.launch(
            fIntent,
            ActivityOptionsCompat.makeCustomAnimation(mActivity, fEnterAnimId, fExitAnimId)
        )
    }

    /**
     * This Method will replace your default method of [startActivity]
     *
     * @param fIntent Your Launcher Screen Intent
     * @param isNeedToFinish [Default value:- false] pass [isNeedToFinish = true] for finish your caller screen after call next screen
     * @param fEnterAnimId your activity Enter animation
     * @param fExitAnimId your activity Exit animation
     */
    open fun launchActivity(
        fIntent: Intent,
        isNeedToFinish: Boolean = false,
        @AnimatorRes @AnimRes fEnterAnimId: Int = android.R.anim.fade_in,
        @AnimatorRes @AnimRes fExitAnimId: Int = android.R.anim.fade_out
    ) {
        mActivity.startActivity(fIntent)
        mActivity.overridePendingTransition(fEnterAnimId, fExitAnimId)

        if (isNeedToFinish) {
            mActivity.finish()
        }
    }

    /**
     * This Method will replace your default method of [onActivityResult]
     *
     * @param requestCode The integer request code originally supplied to launchActivityForResult(), allowing you to identify who this result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @UiThread
    open fun fromActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }
    open fun showKeyboard(view: View?) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    open fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
    }
    protected fun showOnLockscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
        }
    }

    protected fun dismissKeyguard() {
        with(getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                requestDismissKeyguard(this@BaseActivity, null)
            }
        }
    }
}