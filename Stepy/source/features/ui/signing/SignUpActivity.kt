package com.steps.tracker.machine.analyzer.features.ui.signing

import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivitySignUpBinding
import com.steps.tracker.machine.analyzer.features.ui.setupaccount.SelectGenderActivity
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class SignUpActivity : BaseBindingActivity<ActivitySignUpBinding>() {
    private var email = ""
    private var name = ""
    private var pass = ""
    private var repass = ""
    private var isPasswordVisible = false
    private var isRePassVisible = false
    override fun setBinding(layoutInflater: LayoutInflater): ActivitySignUpBinding {
        return ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@SignUpActivity
    }

    override fun initView() {
        super.initView()
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(
            binding.btnSignUp,
            binding.visiblePass,
            binding.visibleRepass,
            binding.txtSignIn
        )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnSignUp -> {
                if (checkValue()) {
                    hideKeyboard()
                    UserRegister()
                } else {
                    Toast.makeText(
                        getActivityContext(), ContextCompat.getString(
                            getActivityContext(),
                            R.string.enter_all_value
                        ), Toast.LENGTH_SHORT
                    ).show()
                }
            }

            binding.visiblePass -> {
                isPasswordVisible = !isPasswordVisible
                if (isPasswordVisible) {
                    binding.password.transformationMethod = null
                    binding.password.setSelection(binding.password.length())
                } else {
                    binding.password.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                    binding.password.setSelection(binding.password.length())
                }
            }

            binding.visibleRepass -> {
                isRePassVisible = !isRePassVisible
                if (isRePassVisible) {
                    binding.repass.transformationMethod = null
                    binding.repass.setSelection(binding.repass.length())
                } else {
                    binding.repass.transformationMethod = PasswordTransformationMethod.getInstance()
                    binding.repass.setSelection(binding.repass.length())
                }
            }

            binding.txtSignIn -> {
                launchActivity(getActivityIntent<SigningActivity> { })
            }
        }
    }

    private fun UserRegister() {
        SharedPreferenceUtils.isSetupAccount = true
        launchActivity(getActivityIntent<SelectGenderActivity> {  })
    }

    private fun checkValue(): Boolean {
        email = binding.email.text.toString()
        name = binding.tvName.text.toString()
        pass = binding.password.text.toString()
        repass = binding.repass.text.toString()

        if (email.isEmpty() || pass.isEmpty() || repass.isEmpty() || name.isEmpty()) {
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        if (pass != repass)
            return false
        return true
    }

}