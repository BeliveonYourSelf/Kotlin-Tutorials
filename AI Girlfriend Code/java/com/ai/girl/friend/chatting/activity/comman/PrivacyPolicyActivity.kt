package com.ai.girl.friend.chatting.activity.comman

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityPrivacyPolicyBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class PrivacyPolicyActivity : BaseBindingActivity<ActivityPrivacyPolicyBinding>() {
    lateinit var OnBackPressedCallback: OnBackPressedCallback
    override fun setBinding(layoutInflater: LayoutInflater): ActivityPrivacyPolicyBinding {
        return ActivityPrivacyPolicyBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@PrivacyPolicyActivity
    }

    override fun initView() {
        super.initView()
        OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                getActivityContext().finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback = OnBackPressedCallback)
        with(binding) {
            wvPrivacyAndPolicy.settings.apply {
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                domStorageEnabled = true
            }
            wvPrivacyAndPolicy.apply {
                webChromeClient = WebChromeClient()
                webViewClient = Webclient()
                loadUrl("https://htcodesprivacypolicy.blogspot.com/2023/12/hi-girl-privacy-policy.html")

            }

        }

    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(back, tvNext)
        }
    }

    class Webclient : WebViewClient() {
        override fun onPageFinished(webView: WebView, str: String) {
            super.onPageFinished(webView, str)

        }

        override fun onPageStarted(webView: WebView, str: String, bitmap: Bitmap?) {
            super.onPageStarted(webView, str, bitmap)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                back -> {
                    onBackPressedDispatcher.onBackPressed()
                }

                tvNext -> {
                    SharedPreferenceUtils.isFirstTime = false
                    if (SharedPreferenceUtils.isLogin) {
                        launchActivity(getActivityIntent<MainActivity> { })
                    } else {
                        launchActivity(getActivityIntent<GuestLoginActivity> { })
                    }
                }
            }
        }
    }

}