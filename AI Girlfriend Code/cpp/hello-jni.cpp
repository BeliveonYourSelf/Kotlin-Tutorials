#include <string.h>
#include <jni.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_getUserInfo(JNIEnv *env,jobject thiz) {
    return env->NewStringUTF(
            "S0nwoPlrz3u0KRv6kOn0DifMdpSfGfk7HPfnxYE0evhEBnQFf3af0szjiyG7TqKRZgT0E4N8rN1cjKYrgHGY7A==");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_saveUserInfo(JNIEnv *env,jobject thiz) {
    return env->NewStringUTF(
            "S0nwoPlrz3u0KRv6kOn0DifMdpSfGfk7HPfnxYE0evjdkVA3LpUFPdxsQtxk84tBp0x8ZZOHcklyEM4hjIwk6g==");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_urlDeleteAccount(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(
            "S0nwoPlrz3u0KRv6kOn0DifMdpSfGfk7HPfnxYE0evitqF3IQKDl5Gdizt2l9flNgLZ8OfBA9drmMQov2J8Dvg==");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_urlLoginWithGoogle(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(
            "S0nwoPlrz3u0KRv6kOn0DifMdpSfGfk7HPfnxYE0evgKSW4vIwezESvQ1VE+e9yBa4OlZKp9LsJBn+jPqiDBkQ==");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_urlLogout(JNIEnv *env,jobject thiz) {
    return env->NewStringUTF("S0nwoPlrz3u0KRv6kOn0DifMdpSfGfk7HPfnxYE0evhr66d+QHxhrcyLr5Psveda");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_rsKey(JNIEnv *env,jobject thiz) {
    return env->NewStringUTF("2b7e151628aed2a6");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_getBaseUrl(JNIEnv *env,jobject thiz) {
    return env->NewStringUTF("http://api.brainshop.ai/");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ai_girl_friend_chatting_appconstants_AppConstant_00024Companion_geminiApiKey(JNIEnv *env,jobject thiz) {
    return env->NewStringUTF("AIzaSyAL0t0qztnRPIiy9o8Kg7b9UeSIZVkpZpE");
}