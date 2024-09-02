plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.ai.girl.friend.chatting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ai.girl.friend.chatting"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        ndkVersion = "27.0.12077973"

    }
    buildFeatures {
        viewBinding = true
        dataBinding =  true
        buildConfig  = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.lottie.v640)
    implementation(libs.intuit.ssp)
    implementation(libs.intuit.sdp)
    implementation(libs.glideDepenacy)
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //Client
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.1")
    //OkHttp Interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")
//Gemini AI
    implementation("com.google.ai.client.generativeai:generativeai:0.7.0")


}