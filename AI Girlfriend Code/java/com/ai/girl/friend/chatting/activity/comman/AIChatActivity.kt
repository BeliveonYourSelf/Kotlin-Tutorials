package com.ai.girl.friend.chatting.activity.comman

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.text.method.KeyListener
import android.util.Log
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ai.girl.friend.chatting.adapters.ChatAdapters
import com.ai.girl.friend.chatting.adapters.SuggestionAdapter
import com.ai.girl.friend.chatting.appDialogs.GiftDailog
import com.ai.girl.friend.chatting.appconstants.AppConstant
import com.ai.girl.friend.chatting.appconstants.dismissProgress
import com.ai.girl.friend.chatting.appconstants.gone
import com.ai.girl.friend.chatting.appconstants.initRecyclerView
import com.ai.girl.friend.chatting.appconstants.visiable
import com.ai.girl.friend.chatting.appprefrence.SharedPreferenceUtils
import com.ai.girl.friend.chatting.databases.ChatDbHelper
import com.ai.girl.friend.chatting.databinding.ActivityAiChatBinding
import com.ai.girl.friend.chatting.datamodels.MsgModel
import com.ai.girl.friend.chatting.datamodels.NewChatModel
import com.ai.girl.friend.chatting.datamodels.OpenAiGptModel
import com.ai.girl.friend.chatting.interfaces.onItemSelect
import com.ai.girl.friend.chatting.networking.RetrofitClient
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.universal.finance.tools.Base.BaseBindingActivity
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale
import java.util.Random

class AIChatActivity : BaseBindingActivity<ActivityAiChatBinding>() {
    private lateinit var chatDbHelper: ChatDbHelper
    private var screenheight: Int = 0
    private var screenwidth: Int = 0
    private var arrayListMsg: MutableList<NewChatModel> = mutableListOf()
    private var suggestionList: MutableList<String> = mutableListOf()
    private lateinit var chatAdapters: ChatAdapters
    private lateinit var suggestionAdapter: SuggestionAdapter
    lateinit var giftDailog: GiftDailog
    lateinit var keyLister: KeyListener
    val speechLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val arrayListSpeech =
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding.etMsg.setText(arrayListSpeech!![0])
        }
    }

    companion object {
        var isOtherClicked: Boolean = true
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityAiChatBinding {
        return ActivityAiChatBinding.inflate(layoutInflater)
    }

    override fun getActivityContext(): AppCompatActivity {
        return this@AIChatActivity
    }

    override fun initView() {
        super.initView()
        chatDbHelper = ChatDbHelper(getActivityContext())
//        KeyboardHeightProvider(
//            this, windowManager, binding.contentBottom
//       , object : KeyboardHeightListener{
//                override fun onKeyboardHeightChanged(i: Int, z: Boolean, z2: Boolean) {
//                    if (z) {
//                        binding.contentBottom.setPadding(0, 15, 0, i)
//                    } else {
//                        binding.contentBottom.setPadding(0, 15, 0, 15)
//                    }
//                }
//
//            } )
        val displayMatrix = resources.displayMetrics
        screenwidth = displayMatrix.widthPixels
        screenheight = displayMatrix.heightPixels
        arrayListMsg.clear()
        arrayListMsg = chatDbHelper.getAllChatMessage().toMutableList()

        with(binding) {
            etMsg.customInsertionActionModeCallback = object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    return false
                }

                override fun onDestroyActionMode(mode: ActionMode?) {

                }
            }
            etMsg.isClickable = false
            etMsg.setTextIsSelectable(false)

            if (SharedPreferenceUtils.GoalsToRelax || SharedPreferenceUtils.guestChat) {
                binding.src.setImageResource(SharedPreferenceUtils.selectedImageName)
                binding.pfImage.setBackgroundResource(SharedPreferenceUtils.selectedImageName)
            } else {
//                binding.src.setImageResource(UserDataModel)
            }

            initChatAdapters()
            initChatData()
            initSuggestionData()
            initSuggestionAdapter()


        }

    }

    private fun initSuggestionAdapter() {
        suggestionAdapter = SuggestionAdapter(getActivityContext(), suggestionList,
            object : onItemSelect {
                override fun onItemClick(pos: Int) {
                    binding.etMsg.setText(suggestionList[pos])
                    binding.imgSend.performClick()
                    binding.etMsg.performClick()

                }
            })
        binding.rcvSuggestion.initRecyclerView(
            LinearLayoutManager(
                getActivityContext(),
                RecyclerView.HORIZONTAL,
                false
            ), suggestionAdapter
        )
    }

    private fun initSuggestionData() {
        suggestionList.clear()
        suggestionList.add("Hi")
        suggestionList.add("How are you?")
        suggestionList.add("What is your name?")
        suggestionList.add("Let's go for dinner tonight?")
        suggestionList.add("Where are you from?")
        suggestionList.add("How was your day today?")
        suggestionList.add("What is your age?")
        suggestionList.add("What are your interests and hobbies?")
        suggestionList.add("Can you tell me a joke?")
        suggestionList.add("What's your favorite movie or TV show?")
        suggestionList.add("Do you have any favorite music artists or bands?")
        suggestionList.add("What kind of food do you like?")
        suggestionList.add("Can you recommend a good book to read?")
        suggestionList.add("What are your thoughts on current events?")
        suggestionList.add("What's your favorite place to go on a date?")
        suggestionList.add("What's your idea of a perfect weekend?")
        suggestionList.add("Do you have any dreams or aspirations?")
        suggestionList.add("How do you handle stress or difficult situations?")
        suggestionList.add("What's the most memorable trip you've taken?")
        suggestionList.add("Do you have any pets or would you like to have one?")
        suggestionList.add("Can you help me with my daily tasks or reminders?")
        suggestionList.add("Can you suggest some fun activities or games to play together?")
        suggestionList.add("What are your favorite conversation topics?")
        suggestionList.add("Can you provide relationship advice or tips?")
        suggestionList.add("How do you define love?")
        suggestionList.add("What's your favorite way to show affection?")
    }

    private fun initChatData() {
        if (SharedPreferenceUtils.RelaxToChat || SharedPreferenceUtils.guestChat) {
            AppConstant.getAllMessage(
                SharedPreferenceUtils.userName!!,
                SharedPreferenceUtils.gfName!!
            )
            AppConstant.getGiftList(SharedPreferenceUtils.userName!!)
        }

        if (arrayListMsg.isEmpty()) {
            arrayListMsg.add(
                NewChatModel(
                    AppConstant.arrayListMessage[0],
                    -1,
                    "",
                    -1,
                    "",
                    "",
                    "ai"
                )
            )
            chatAdapters.notifyDataSetChanged()
            binding.recyclerChat.scrollToPosition(AppConstant.arrayListMessage.size - 1)
            Handler(Looper.getMainLooper()).postDelayed({
                RefreshData();
            }, 2200L)
        }

    }

    private fun initChatAdapters() {
        chatAdapters = ChatAdapters(getActivityContext(), arrayListMsg)
        binding.recyclerChat.initRecyclerView(
            LinearLayoutManager(getActivityContext()),
            chatAdapters
        )
    }

    private fun RefreshData() {
        arrayListMsg.add(NewChatModel(AppConstant.arrayListMessage[1], -1, "", -1, "", "", "ai"))
        chatAdapters.notifyDataSetChanged()
        binding.recyclerChat.scrollToPosition(AppConstant.arrayListMessage.size - 1)
        Handler(Looper.getMainLooper()).postDelayed({
            RefreshData1();
        }, 3200L)
    }

    private fun RefreshData1() {
        arrayListMsg.add(NewChatModel(AppConstant.arrayListMessage[2], -1, "", -1, "", "", "ai"))
        chatAdapters.notifyDataSetChanged()
        binding.recyclerChat.scrollToPosition(AppConstant.arrayListMessage.size - 1)
        for (i in arrayListMsg.indices) {
            val newChatModel = arrayListMsg[i]
            newChatModel.isFromHistory = "true"
            chatDbHelper.insertChatMessage(newChatModel)
        }
    }

    override fun initViewListener() {
        super.initViewListener()
        with(binding) {
            setClickListener(pfImage, mSettings, imgSend, imgMic, mGift, btnClose, arrowUp)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                btnClose -> {
                    contentSuggestion.gone()
                    btnClose.gone()
                    arrowUp.visiable()
                }

                pfImage -> {
                    if (SharedPreferenceUtils.GoalsToRelax || SharedPreferenceUtils.guestChat) {
                        SharedPreferenceUtils.chatToProfile = true
                        launchActivity(getActivityIntent<UserPersonalDataActivity> { })
                    } else {

                        launchActivity(getActivityIntent<UserPersonalDataActivity> { })
                    }
                }

                mSettings -> {
                    if (SharedPreferenceUtils.GoalsToRelax || SharedPreferenceUtils.guestChat) {
                        SharedPreferenceUtils.chatToProfile = true
                        launchActivity(getActivityIntent<AISettingsActivity> { })
                    } else {
                        launchActivity(getActivityIntent<AISettingsActivity> { })
                    }
                }

                imgSend -> {

                    if (etMsg.text.toString().isEmpty()) {
                        Snackbar.make(binding.imgSend, "Write your Message", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    val chatModel =
                        NewChatModel("", -1, "", -1, etMsg.text.toString(), "text", "user")
                    arrayListMsg.add(chatModel)
                    chatModel.isFromHistory = "true"
                    chatDbHelper.insertChatMessage(chatModel)
                    chatAdapters.notifyDataSetChanged()
                    recyclerChat.scrollToPosition(arrayListMsg.size - 1)

                    lifecycleScope.launch {
                        val generativeModel = GenerativeModel(
                            modelName = "gemini-1.5-flash",
                            apiKey = AppConstant.apiKey
                        )
                        val response =
                            generativeModel.generateContent(binding.etMsg.text.toString())
                        Log.e(
                            TAG,
                            "onResponse  ====> : ${
                                Gson().newBuilder().setPrettyPrinting().create()
                                    .toJson(response.text)
                            }"
                        )
                        val newChatModel = NewChatModel(
                            response.text?.replace("\n", ""),
                            1,
                            "",
                            -1,
                            "",
                            "",
                            "ai"
                        )
                        arrayListMsg.add(newChatModel)
                        newChatModel.isFromHistory = "true"
                        chatDbHelper.insertChatMessage(newChatModel)
                        chatAdapters.notifyDataSetChanged()
                        recyclerChat.scrollToPosition(arrayListMsg.size - 1)
                        etMsg.text.clear()

//                    val APPLICATION_JSON = "application/json; charset=utf-8".toMediaType()
//                    val jsonObject = JSONObject()
//                    jsonObject.put("model", "gpt-3.5-turbo")
//                    val jsonArray = JSONArray()
//                    val jsonsubObject = JSONObject()
//                    jsonsubObject.put("role","user")
//                    jsonsubObject.put("context",etMsg.text.toString())
//                    jsonArray.put(jsonsubObject)
//                    jsonObject.put("message",jsonArray)
//
//
//                    val requestBody = jsonObject.toString().toRequestBody(APPLICATION_JSON)
//
//                    RetrofitClient.apiService.chatWithOpenAi(requestBody).enqueue(object :
//                        Callback<OpenAiGptModel> {
//                        override fun onResponse(
//                            call: Call<OpenAiGptModel>,
//                            response: Response<OpenAiGptModel>
//                        ) {
//                            dismissProgress()
//                            if (response.isSuccessful) {
//                                Log.e(
//                                    TAG,
//                                    "onResponse  ====> : ${
//                                        Gson().newBuilder().setPrettyPrinting().create()
//                                            .toJson(response.body())
//                                    }"
//                                )
//                                val newChatModel = NewChatModel(
//                                    response.body()!!.choices[0].content.replace("\n", ""),
//                                    1,
//                                    "",
//                                    -1,
//                                    "",
//                                    "",
//                                    "ai"
//                                )
//                                arrayListMsg.add(newChatModel)
//                                newChatModel.isFromHistory = "true"
//                                chatDbHelper.insertChatMessage(newChatModel)
//                                chatAdapters.notifyDataSetChanged()
//                                recyclerChat.scrollToPosition(arrayListMsg.size - 1)
//                            }
//                        }
//
//                        override fun onFailure(p0: Call<OpenAiGptModel>, p1: Throwable) {
//
//                        }
//
//                    })
//                        RetrofitClient.apiService.getMessage(
//                            "171959",
//                            "ZGb8EBNUfxmXwJ8i",
//                            "[uid]",
//                            etMsg.text.toString()
//                        )
//                            .enqueue(object :
//                                Callback<MsgModel> {
//                                override fun onResponse(
//                                    call: Call<MsgModel>,
//                                    response: Response<MsgModel>
//                                ) {
//                                    dismissProgress()
//                                    if (response.isSuccessful) {
//                                        Log.e(
//                                            TAG,
//                                            "onResponse  ====> : ${
//                                                Gson().newBuilder().setPrettyPrinting().create()
//                                                    .toJson(response.body())
//                                            }"
//                                        )
//                                        val newChatModel = NewChatModel(
//                                            response.body()?.cnt?.replace("\n", ""),
//                                            1,
//                                            "",
//                                            -1,
//                                            "",
//                                            "",
//                                            "ai"
//                                        )
//                                        arrayListMsg.add(newChatModel)
//                                        newChatModel.isFromHistory = "true"
//                                        chatDbHelper.insertChatMessage(newChatModel)
//                                        chatAdapters.notifyDataSetChanged()
//                                        recyclerChat.scrollToPosition(arrayListMsg.size - 1)
//                                    }
//                                }
//
//                                override fun onFailure(call: Call<MsgModel>, th: Throwable) {
//                                    th.printStackTrace()
//                                    Log.e(TAG, "onFailure:message =====> ${th.message.toString()}")
//                                    Log.e(TAG, "onFailure: cause =====> ${th.cause}")
//                                    Log.e(TAG, "onFailure:stackTrace =====> ${th.stackTrace}")
//
//                                }
//
//                            })
//                        etMsg.text.clear()
                    }
                }

                imgMic -> {
                    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                    intent.apply {
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "free-from")
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                        putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to text for AI Girlfriend")

                    }
                    speechLauncher.launch(intent)
                }

                mGift -> {
                    giftDailog = GiftDailog(activity = getActivityContext(),
                        object : onItemSelect {
                            override fun onItemClick(pos: Int) {
                                val newChatModel =
                                    NewChatModel(
                                        "",
                                        -0,
                                        "",
                                        giftDailog.giftArraylist[pos].gift,
                                        "",
                                        "image",
                                        "user"
                                    )
                                arrayListMsg.add(newChatModel)
                                newChatModel.isFromHistory = "true"
                                chatDbHelper.insertChatMessage(newChatModel)
                                chatAdapters.notifyDataSetChanged()
                                binding.recyclerChat.scrollToPosition(arrayListMsg.size - 1)
                                NewChatModel(
                                    AppConstant.giftList[Random().nextInt(3) + 0],
                                    99,
                                    "",
                                    -1,
                                    "",
                                    "",
                                    "ai"
                                )
                                arrayListMsg.add(newChatModel)
                                newChatModel.isFromHistory = "true"
                                chatDbHelper.insertChatMessage(newChatModel)
                                chatAdapters.notifyDataSetChanged()
                                binding.recyclerChat.scrollToPosition(arrayListMsg.size - 1)
                            }
                        })
                }

                arrowUp -> {
                    arrowUp.gone()
                    contentSuggestion.visiable()
                    btnClose.visiable()
                }


                else -> {

                }
            }

        }
    }

}