package com.ai.girl.friend.chatting.activity.comman

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.appconstants.AppConstant
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityGenderBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class GenderActivity : BaseBindingActivity<ActivityGenderBinding>() {
    var userName: String = ""
    var mSelectedText: String = ""
    var nameToPronounce: Boolean = false;
    val HE = "HE"
    val SHE = "SHE"
    val THEY = "THEY"
    override fun setBinding(layoutInflater: LayoutInflater): ActivityGenderBinding {
        return ActivityGenderBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@GenderActivity
    }

    override fun initView() {
        super.initView()
        val intentData = intent.extras
        userName = intentData?.getString(AppConstant.NAME, "").toString()
        nameToPronounce = intentData!!.getBoolean(AppConstant.NAMETOPRONOUNS, false)

    }

    override fun initViewAction() {
        super.initViewAction()
        with(binding) {
            if (nameToPronounce) {
                headerText.text = "Hi ${userName} !"
            }

        }


    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(back,btnMale,btnFemale,btnThey,tvNext)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                back -> {
                    onBackPressed()
                }
                tvNext ->{
                    if(mSelectedText.isEmpty())
                    {
                        Toast.makeText(
                            getActivityContext(),
                            "Please Select Any on Above",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    if(nameToPronounce)
                    {
                    SharedPreferenceUtils.pronounce=mSelectedText
                    SharedPreferenceUtils.pronounsToSelectCharacter=true

                        launchActivity(getActivityIntent<SelectAiGirlActivity> {
                            putBoolean(AppConstant.PRONOUNSTOSELECTCHARACTER,true)
                        })
                    }
                }


                btnMale -> {
                    btnMale.setBackgroundResource(R.drawable.bg_border_filled)
                    btnMale.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    btnFemale.setBackgroundResource(R.drawable.bg_border)
                    btnFemale.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    btnThey.setBackgroundResource(R.drawable.bg_border)
                    btnThey.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    mSelectedText= HE
                }

                btnFemale -> {
                    btnMale.setBackgroundResource(R.drawable.bg_border)
                    btnMale.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    btnFemale.setBackgroundResource(R.drawable.bg_border_filled)
                    btnFemale.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    btnThey.setBackgroundResource(R.drawable.bg_border)
                    btnThey.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                        mSelectedText= SHE
                }

                btnThey -> {
                    btnMale.setBackgroundResource(R.drawable.bg_border)
                    btnMale.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    btnFemale.setBackgroundResource(R.drawable.bg_border)
                    btnFemale.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    btnThey.setBackgroundResource(R.drawable.bg_border_filled)
                    btnThey.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    mSelectedText= THEY
                }
            }
        }
    }

}
