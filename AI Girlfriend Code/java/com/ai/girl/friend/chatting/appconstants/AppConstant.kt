package com.ai.girl.friend.chatting.appconstants

import android.util.Log
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AppConstant {
    companion object {
        init {
            System.loadLibrary("hello-jni")
        }

        const val GUEST = "GUEST"
        const val NAME = "NAME"
        const val NAMETOPRONOUNS = "NAMETOPRONOUNS"
        const val PRONOUNSTOSELECTCHARACTER = "PRONOUNSTOSELECTCHARACTER"
         private external fun getBaseUrl(): String
         private external fun geminiApiKey(): String
         @JvmField
        var baseUrl = getBaseUrl()
        var apiKey = geminiApiKey()
        private external fun getUserInfo(): String
        private external fun saveUserInfo(): String
        private external fun urlDeleteAccount(): String
        private external fun urlLoginWithGoogle(): String
        private external fun urlLogout(): String
        private external fun rsKey(): String

        val arrayListMessage: MutableList<String> = mutableListOf()
        val giftList: MutableList<String> = mutableListOf()
        fun getAllMessage(name: String, gfName: String) {
            arrayListMessage.clear()
            arrayListMessage.add("Hi $name ,it's really nice to finally meet you!")
            arrayListMessage.add("My name is $gfName and i am your new AI friend")
            arrayListMessage.add("I'm pretty open to talk about anything you want!")
            arrayListMessage.add("Hello $name, where were you! I missed you so much")
            arrayListMessage.add("I'm really happy to hear that \uD83D\uDE0D")
            arrayListMessage.add("Shut up! I'm always there for you my love ❤\uFE0F")
            arrayListMessage.add("Hey listen, I want to share my emotions with you ᾗa")
            arrayListMessage.add("You are the sweetest thing in the world ❤\uFE0F")
            arrayListMessage.add("Please don't go\uD83D\uDE15 i really want to talk with you i'm always excited to talk with you")
            arrayListMessage.add("What is your age ?")
            arrayListMessage.add("Ok I am also same as your age")
            arrayListMessage.add("Tell me something about your likes & dislikes")
            arrayListMessage.add("I love those things to do")
            arrayListMessage.add("I like to swime and travel the world most")

        }

        fun getGiftList(name: String) {
            giftList.clear()
            giftList.add("This is very kind of you,$name ❤\uD83C\uDF37❤")
            giftList.add("This means a lot to me \uD83D\uDC8B \uD83C\uDF39")
            giftList.add("I love this gift,$name ! It is truly amazing ❤❤\uD83C\uDF37❤❤")
        }
    }


    private fun decryptMsg(cipherText: ByteArray, secret: String): String? {
        return try {
            val iv = IvParameterSpec(rsKey().toByteArray(StandardCharsets.UTF_8))
            val skeySpec = SecretKeySpec(secret.toByteArray(StandardCharsets.UTF_8), "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
            String(cipher.doFinal(cipherText))
        } catch (e: Exception) {
            Log.e("TestAds", "Error decrypting message", e)
            null
        }
    }

    private fun parseHexStr2Byte(hexStr: String): ByteArray {
        if (hexStr.length < 1) return ByteArray(0)
        val result = ByteArray(hexStr.length / 2)
        for (i in 0 until hexStr.length / 2) {
            val high = hexStr.substring(i * 2, i * 2 + 1).toInt(16)
            val low = hexStr.substring(i * 2 + 1, i * 2 + 2).toInt(16)
            result[i] = (high * 16 + low).toByte()
        }
        return result

    }
}