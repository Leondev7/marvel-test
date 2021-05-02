
object Versions {
    //GLOBAL
    const val gradle = "4.1.3"
    const val kotlin = "1.4.32"

    //ANDROID
    const val appcompat = "1.2.0"
    const val material = "1.1.0"
    const val fragment = "1.2.5"
    const val coreKTX = "1.2.0"
    const val constraintLayout = "1.1.3"
    const val legacy = "1.0.0"
    const val lifecycle= "2.3.1"
    const val navigation = "2.3.5"
    const val recyclerView = "1.1.0"
    const val swipeRefresh = "1.1.0"

    //KTOR
    const val ktor = "1.5.2"

    //HILT
    const val hilt = "1.0.0-alpha03"
    const val daggerHilt = "2.35.1"

    //GLIDE
    const val glide = "4.11.0"

}

object BuildConfig {
    const val APPLICATION_ID = "com.leondev7.marveltest"

    const val BUILD_TOOLS_VERSION = "30.0.3"
    const val COMPILE_SDK_VERSION = 30
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = COMPILE_SDK_VERSION

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

}


object Android{
    const val appcompatX = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val materialX = "com.google.android.material:material:${Versions.material}"
    const val fragmentX = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"
    const val constraintLayoutX = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val viewmodelX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val swypeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
}

object Navigation{
    const val navUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
}

object Ktor{
    val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    val clientGson =  "io.ktor:ktor-client-gson:${Versions.ktor}"
    val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    val clientCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
}

object Hilt{
    val hilt ="com.google.dagger:hilt-android:${Versions.daggerHilt}"
    val compiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
    val common = "androidx.hilt:hilt-common:${Versions.hilt}"

}

object Glide{
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Kotlin{
    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
}


object BuildPlugins{
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

}