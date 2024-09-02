package com.ai.girl.friend.chatting.networking

import com.ai.girl.friend.chatting.datamodels.MsgModel
import com.ai.girl.friend.chatting.datamodels.OpenAiGptModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("get")
    fun getMessage(@Query("bid") bid: String,@Query("key") key: String,@Query("uid") uid: String,@Query("message") msg: String): Call<MsgModel>

    fun chatWithOpenAi(@Body requestBody: RequestBody) : Call<OpenAiGptModel>
}