plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.developer.ksp)
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.steps.tracker.machine.analyzer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.steps.tracker.machine.analyzer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    implementation(libs.firebase.auth)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    /*SDP*/
    implementation(libs.intuit.sdp)
    implementation(libs.intuit.ssp)
    /*Lottie*/
    implementation(libs.lottie.v640)
    /*Glide*/
    implementation(libs.glideDepenacy)
    implementation(libs.glide.compiler)
    /*Google Gson*/
    implementation(libs.google.gson)

    /*RxJava*/
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    // HILT



    /*Room Database*/
    implementation(libs.androidx.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.androidx.preference)

    /*Horizontal Number Picker*/
    implementation(libs.number.picker)

    /*Refresh Layout*/
    implementation(libs.androidx.swiperefreshlayout)
}