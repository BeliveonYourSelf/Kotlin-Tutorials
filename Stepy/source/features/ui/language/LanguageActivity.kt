package com.steps.tracker.machine.analyzer.features.ui.language

import android.content.res.Resources
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.steps.tracker.machine.analyzer.R
import com.steps.tracker.machine.analyzer.adapters.LanguageAdapter
import com.steps.tracker.machine.analyzer.base.BaseBindingActivity
import com.steps.tracker.machine.analyzer.databinding.ActivityLanguageBinding
import com.steps.tracker.machine.analyzer.features.ui.signing.SigningActivity
import com.steps.tracker.machine.analyzer.prefrences.SharedPreferenceUtils

class LanguageActivity : BaseBindingActivity<ActivityLanguageBinding>() {
    var listLanguage: ArrayList<Language> = arrayListOf()
    var languageCode = "en"
    val OPEN_FROM_MAIN = "open_from_main"
    lateinit var languageAdapter: LanguageAdapter
    override fun setBinding(layoutInflater: LayoutInflater): ActivityLanguageBinding {
        return ActivityLanguageBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@LanguageActivity
    }

    override fun initView() {
        super.initView()
        getLanguageData()
    }

    private fun getLanguageData() {
        initData()
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Resources.getSystem().configuration.locales[0]
        } else {
            Resources.getSystem().configuration.locale
        }
        var languageSystem: Language? = null
        var position = 0
        for (language in listLanguage) {
            if (intent.getBooleanExtra(OPEN_FROM_MAIN, false)) {
                if (SharedPreferenceUtils.languageCode == language.locale) {
                    languageSystem = language
                    languageCode = languageSystem.locale
                }
            } else
                if (language.locale.equals(locale.language)) {
                    languageSystem = language
                    languageCode = locale.language
                }
        }

        if (languageSystem != null) {
            listLanguage.remove(languageSystem)
            listLanguage.add(0, languageSystem)
        }
        listLanguage[position].isChoose = true
        initAdapter()
    }

    private fun initAdapter() {
        languageAdapter = LanguageAdapter(this, object : LanguageAdapter.OnLanguageClickListener {
            override fun onClickItemListener(language: Language?) {
                languageCode = language!!.locale
            }
        })
        languageAdapter.setItems(listLanguage)
        binding.recyclerViewLanguage.adapter = languageAdapter
    }


    private fun initData() {
        listLanguage = ArrayList()
        listLanguage.add(Language(R.drawable.flag_en, getString(R.string.language_english), "en"))
        listLanguage.add(
            Language(
                R.drawable.flag_vn_vietnam,
                getString(R.string.language_vietnam),
                "vi"
            )
        )
        listLanguage.add(
            Language(
                R.drawable.flag_fr_france,
                getString(R.string.language_france),
                "fr"
            )
        )
        listLanguage.add(
            Language(
                R.drawable.flag_es_spain,
                getString(R.string.language_spain),
                "es"
            )
        )

        listLanguage.add(
            Language(
                R.drawable.flag_de_germany,
                getString(R.string.language_germany),
                "de"
            )
        )
        listLanguage.add(
            Language(
                R.drawable.flag_ko_korean,
                getString(R.string.language_korean),
                "ko"
            )
        )
    }

    override fun initViewListener() {
        super.initViewListener()
        setClickListener(binding.imgConfirm)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imgConfirm ->{
                changeLanguage()
            }
        }
    }

    private fun changeLanguage() {
        SharedPreferenceUtils.languageCode =languageCode
        LanguageUtil.changeLang(SharedPreferenceUtils.languageCode!!, this)
        SharedPreferenceUtils.firstOpenApp = false
        launchActivity(getActivityIntent<SigningActivity> {  })
    }

}