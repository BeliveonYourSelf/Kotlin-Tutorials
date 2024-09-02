package com.steps.tracker.machine.analyzer.features.ui.signing

import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.apputility.showProgress
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivitySigningBinding

class SigningActivity : BaseBindingActivity<ActivitySigningBinding>() {
    private var email = ""
    private var pass = ""
    private var isPasswordVisible = false

    override fun setBinding(layoutInflater: LayoutInflater): ActivitySigningBinding {
        return ActivitySigningBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SigningActivity
    }

    override fun initView() {
        super.initView()

        binding.tvPassword.setOnEditorActionListener(object : OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (checkValues()) {
                        hideKeyboard()
                        showProgress("Please Wait..",this@SigningActivity)
                        // TODO: SignIn ProcessLeft
                    } else {
                        Toast.makeText(
                            getActivityContext(),
                            ContextCompat.getString(getActivityContext(), R.string.enter_all_value),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return true
                } else {
                    return false
                }
            }

        })
    }

    private fun checkValues(): Boolean {
        email = binding.tvEmail.text.toString()
        pass = binding.tvPassword.text.toString()
        if (email.isEmpty() || pass.isEmpty()) {
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        return true
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(
            binding.btnSignin,
            binding.txtSignup,
            binding.txtfogotpass,
            binding.ivVisiblePass
        )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSignin -> {
                if (checkValues()) {
                    hideKeyboard()
                    // TODO: SignIn ProcessLeft
                } else {
                    Toast.makeText(
                        getActivityContext(),
                        ContextCompat.getString(getActivityContext(), R.string.enter_all_value),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            binding.ivVisiblePass -> {
                isPasswordVisible = !isPasswordVisible
                if (isPasswordVisible) {
                    binding.tvPassword.transformationMethod = null
                    binding.tvPassword.setSelection(binding.tvPassword.length())
                } else {
                    binding.tvPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                    binding.tvPassword.setSelection(binding.tvPassword.length())
                }
            }

            binding.txtSignup -> {
                launchActivity(getActivityIntent<SignUpActivity> { })
            }

            binding.txtfogotpass -> {
                launchActivity(getActivityIntent<ForgotPasswordActivity> { })
            }

        }
    }


}