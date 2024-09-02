package com.ai.girl.friend.chatting.activity.comman

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ai.girl.friend.chatting.R
import com.ai.girl.friend.chatting.appconstants.gone
import com.ai.girl.friend.chatting.appconstants.visiable
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databinding.ActivityPeaceFulBinding
import com.universal.finance.tools.Base.BaseBindingActivity

class PeaceFulActivity : BaseBindingActivity<ActivityPeaceFulBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityPeaceFulBinding {
        return ActivityPeaceFulBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@PeaceFulActivity
    }

    override fun initView() {
        super.initView()
    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(
                back,
                tvNext,
                readBook,
                watchingTV,
                goingRun,
                laughChat,
                playGames,
                playGame,
                deep
            )
        }

    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                back -> {

                }

                tvNext -> {
                    if (SharedPreferenceUtils.GoalsToRelax) {

                        toolbar.gone()
                        conMain.gone()
                        content2.visiable()
                        Handler(Looper.getMainLooper())
                            .postDelayed({
                                tvMsg2.setTextColor(
                                    ContextCompat.getColor(
                                        getActivityContext(),
                                        R.color.white
                                    )
                                )
                                img2.setImageResource(R.drawable.ic_checkbox_circle_fill)
                            }, 1500L)

                        Handler(Looper.getMainLooper()).postDelayed({
                            tv3.setTextColor(
                                ContextCompat.getColor(
                                    getActivityContext(),
                                    R.color.white
                                )
                            )
                            img3.setImageResource(R.drawable.ic_checkbox_circle_fill)
                        }, 2900L)

                        Handler(Looper.getMainLooper()).postDelayed({
                            SharedPreferenceUtils.GoalsToRelax = true
                            SharedPreferenceUtils.RelaxToChat = true
                            SharedPreferenceUtils.isIntroComplete = true
                            launchActivity(getActivityIntent<AIChatActivity> { })
                        }, 5000L)


                    }
                }

                readBook -> {
                    readBook.setBackgroundResource(R.drawable.bg_border_filled)
                    readBook.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )

                    watchingTV.setBackgroundResource(R.drawable.bg_border)
                    watchingTV.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    goingRun.setBackgroundResource(R.drawable.bg_border)
                    goingRun.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    laughChat.setBackgroundResource(R.drawable.bg_border)
                    laughChat.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGames.setBackgroundResource(R.drawable.bg_border)
                    playGames.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGame.setBackgroundResource(R.drawable.bg_border)
                    playGame.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    deep.setBackgroundResource(R.drawable.bg_border)
                    deep.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                }

                watchingTV -> {
                    watchingTV.setBackgroundResource(R.drawable.bg_border_filled)
                    watchingTV.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )

                    readBook.setBackgroundResource(R.drawable.bg_border)
                    readBook.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    goingRun.setBackgroundResource(R.drawable.bg_border)
                    goingRun.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    laughChat.setBackgroundResource(R.drawable.bg_border)
                    laughChat.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGames.setBackgroundResource(R.drawable.bg_border)
                    playGames.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGame.setBackgroundResource(R.drawable.bg_border)
                    playGame.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    deep.setBackgroundResource(R.drawable.bg_border)
                    deep.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                }

                goingRun -> {
                    goingRun.setBackgroundResource(R.drawable.bg_border_filled)
                    goingRun.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )

                    readBook.setBackgroundResource(R.drawable.bg_border)
                    readBook.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    watchingTV.setBackgroundResource(R.drawable.bg_border)
                    watchingTV.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    laughChat.setBackgroundResource(R.drawable.bg_border)
                    laughChat.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGames.setBackgroundResource(R.drawable.bg_border)
                    playGames.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGame.setBackgroundResource(R.drawable.bg_border)
                    playGame.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    deep.setBackgroundResource(R.drawable.bg_border)
                    deep.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                }

                laughChat -> {
                    laughChat.setBackgroundResource(R.drawable.bg_border_filled)
                    laughChat.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )

                    readBook.setBackgroundResource(R.drawable.bg_border)
                    readBook.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    watchingTV.setBackgroundResource(R.drawable.bg_border)
                    watchingTV.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    goingRun.setBackgroundResource(R.drawable.bg_border)
                    goingRun.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGames.setBackgroundResource(R.drawable.bg_border)
                    playGames.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGame.setBackgroundResource(R.drawable.bg_border)
                    playGame.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    deep.setBackgroundResource(R.drawable.bg_border)
                    deep.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                }

                playGames -> {
                    playGames.setBackgroundResource(R.drawable.bg_border_filled)
                    playGames.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )

                    readBook.setBackgroundResource(R.drawable.bg_border)
                    readBook.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    watchingTV.setBackgroundResource(R.drawable.bg_border)
                    watchingTV.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    goingRun.setBackgroundResource(R.drawable.bg_border)
                    goingRun.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    laughChat.setBackgroundResource(R.drawable.bg_border)
                    laughChat.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGame.setBackgroundResource(R.drawable.bg_border)
                    playGame.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    deep.setBackgroundResource(R.drawable.bg_border)
                    deep.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                }

                playGame -> {
                    playGame.setBackgroundResource(R.drawable.bg_border_filled)
                    playGame.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )

                    readBook.setBackgroundResource(R.drawable.bg_border)
                    readBook.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    watchingTV.setBackgroundResource(R.drawable.bg_border)
                    watchingTV.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    goingRun.setBackgroundResource(R.drawable.bg_border)
                    goingRun.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    laughChat.setBackgroundResource(R.drawable.bg_border)
                    laughChat.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGames.setBackgroundResource(R.drawable.bg_border)
                    playGames.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    deep.setBackgroundResource(R.drawable.bg_border)
                    deep.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.white))
                }

                deep -> {
                    deep.setBackgroundResource(R.drawable.bg_border_filled)
                    deep.setTextColor(ContextCompat.getColor(getActivityContext(), R.color.black))

                    readBook.setBackgroundResource(R.drawable.bg_border)
                    readBook.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.black
                        )
                    )
                    watchingTV.setBackgroundResource(R.drawable.bg_border)
                    watchingTV.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    goingRun.setBackgroundResource(R.drawable.bg_border)
                    goingRun.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    laughChat.setBackgroundResource(R.drawable.bg_border)
                    laughChat.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGames.setBackgroundResource(R.drawable.bg_border)
                    playGames.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                    playGame.setBackgroundResource(R.drawable.bg_border)
                    playGame.setTextColor(
                        ContextCompat.getColor(
                            getActivityContext(),
                            R.color.white
                        )
                    )
                }
            }
        }
    }

}