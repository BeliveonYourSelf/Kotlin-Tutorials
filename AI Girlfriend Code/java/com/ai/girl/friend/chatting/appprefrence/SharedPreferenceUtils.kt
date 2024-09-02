package com.ai.girl.friend.chatting.appprefrence

object SharedPreferenceUtils {

    private const val FIRST_TIME = "FIRST_TIME"
    private const val ISINTRO_COMPLETE = "ISINTRO_COMPLETE"
    private const val IS_LOGIN = "IS_LOGIN"
    private const val USERNAME = "USERNAME"
    private const val GFNAME = "GFNAME"
    private const val PRONOUNCE = "PRONOUNCE"
    private const val PRONOUNSTOSELECTCHARACTER = "PRONOUNSTOSELECTCHARACTER"
    private const val SELECTEDIMAGE = "SELECTEDIMAGE"
    private const val SELECTEDIMAGE_POS = "SELECTEDIMAGE_POS"
    private const val SELECTCHARTOPERSONALITY = "SELECTCHARTOPERSONALITY"
    private const val PERSONALITYTOAIGIRLNAME = "PERSONALITYTOAIGIRLNAME"
    private const val AIGIRLTOINTEREST = "AIGIRLTOINTEREST"
    private const val INTERESTTOGOAL = "INTERESTTOGOAL"
    private const val GOALSTORELAX = "GOALSTORELAX"
    private const val RELAXTOCHAT = "RELAXTOCHAT"
    private const val GUEST_CHAT = "GUEST_CHAT"
    private const val CHAT_TO_PROFILE = "CHAT_TO_PROFILE"
    var isFirstTime: Boolean
        get() = sharedPreferencesManager.getValueBool(FIRST_TIME, false)
        set(value) = sharedPreferencesManager.setValueBool(FIRST_TIME, value)

    var isIntroComplete: Boolean
        get() = sharedPreferencesManager.getValueBool(ISINTRO_COMPLETE, false)
        set(value) = sharedPreferencesManager.setValueBool(ISINTRO_COMPLETE, value)

    var isLogin: Boolean
        get() = sharedPreferencesManager.getValueBool(IS_LOGIN, false)
        set(value) = sharedPreferencesManager.setValueBool(ISINTRO_COMPLETE, value)
    var guestChat: Boolean
        get() = sharedPreferencesManager.getValueBool(GUEST_CHAT, false)
        set(value) = sharedPreferencesManager.setValueBool(GUEST_CHAT, value)
    var userName: String?
        get() = sharedPreferencesManager.getValue(USERNAME, "")
        set(value) = sharedPreferencesManager.setValue(USERNAME, value)
    var pronounce: String?
        get() = sharedPreferencesManager.getValue(PRONOUNCE)
        set(value) = sharedPreferencesManager.setValue(PRONOUNCE, value)
    var pronounsToSelectCharacter: Boolean
        get() = sharedPreferencesManager.getValueBool(PRONOUNSTOSELECTCHARACTER, false)
        set(value) = sharedPreferencesManager.setValueBool(PRONOUNSTOSELECTCHARACTER, value)
    var selectedImageName: Int
        get() = sharedPreferencesManager.getIntValue(SELECTEDIMAGE)
        set(value) = sharedPreferencesManager.setIntValue(SELECTEDIMAGE, value)
    var selectedImagePosition: Int
        get() = sharedPreferencesManager.getIntValue(SELECTEDIMAGE_POS, 0)
        set(value) = sharedPreferencesManager.setIntValue(SELECTEDIMAGE_POS, value)
    var selectCharToPersonality: Boolean
        get() = sharedPreferencesManager.getValueBool(SELECTCHARTOPERSONALITY, false)
        set(value) = sharedPreferencesManager.setValueBool(SELECTCHARTOPERSONALITY, value)
    var personalityToAiGirlName: Boolean
        get() = sharedPreferencesManager.getValueBool(PERSONALITYTOAIGIRLNAME, false)
        set(value) = sharedPreferencesManager.setValueBool(PERSONALITYTOAIGIRLNAME, value)
    var AiGirlToInterest: Boolean
        get() = sharedPreferencesManager.getValueBool(AIGIRLTOINTEREST, false)
        set(value) = sharedPreferencesManager.setValueBool(AIGIRLTOINTEREST, value)
    var InterestToGoal: Boolean
        get() = sharedPreferencesManager.getValueBool(INTERESTTOGOAL, false)
        set(value) = sharedPreferencesManager.setValueBool(INTERESTTOGOAL, value)
    var GoalsToRelax: Boolean
        get() = sharedPreferencesManager.getValueBool(GOALSTORELAX, false)
        set(value) = sharedPreferencesManager.setValueBool(GOALSTORELAX, value)
    var RelaxToChat: Boolean
        get() = sharedPreferencesManager.getValueBool(RELAXTOCHAT, false)
        set(value) = sharedPreferencesManager.setValueBool(RELAXTOCHAT, value)
    var gfName: String?
        get() = sharedPreferencesManager.getValue(GFNAME, "")
        set(value) = sharedPreferencesManager.setValue(GFNAME, value)
    var chatToProfile : Boolean
        get() =  sharedPreferencesManager.getValueBool(GOALSTORELAX,false)
        set(value) = sharedPreferencesManager.setValueBool(GOALSTORELAX,value)


}