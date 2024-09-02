package com.ai.girl.friend.chatting.datamodels

import com.google.gson.annotations.SerializedName

data class OpenAiGptModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("choices")
    val choices: List<Message>
) {
    data class Message(
        @SerializedName("role")
        val role: String,
        @SerializedName("content")
        val content: String

    )


}
