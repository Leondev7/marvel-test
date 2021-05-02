import extensions.buildConfigStringField
import extensions.getLocalProperty

plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id( "kotlin-kapt")
    id("dagger.hilt.android.plugin")
}
android {
    compileSdkVersion(BuildConfig.COMPILE_SDK_VERSION)
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdkVersion(BuildConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildConfig.TARGET_SDK_VERSION)
        versionCode = BuildConfig.VERSION_CODE
        versionName = BuildConfig.VERSION_NAME
        testInstrumentationRunner = BuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }


    buildTypes.forEach {

        it.buildConfigStringField("MARVEL_API_BASE_URL", "gateway.marvel.com")
        it.buildConfigStringField("CHARACTER_LIST_ENDPOINT", "/v1/public/characters")
        it.buildConfigStringField("CHARACTER_ENDPOINT", "/v1/public/characters")
        it.buildConfigStringField("MARVEL_API_KEY_PUBLIC", getLocalProperty("marvel.key.public"))
        it.buildConfigStringField("MARVEL_API_KEY_PRIVATE", getLocalProperty("marvel.key.private"))
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //Kotlin
    implementation (Kotlin.stdlib)
    //Android
    implementation (Android.coreKTX)
    implementation (Android.materialX)
    implementation (Android.constraintLayoutX)
    implementation (Android.appcompatX)

    //Ktor
    implementation(Ktor.clientCore)
    implementation(Ktor.clientCio)
    implementation(Ktor.clientLogging)
    implementation(Ktor.clientGson)
    implementation(Ktor.clientJson)

    //Hilt
    implementation (Hilt.hilt)
    kapt(Hilt.compiler)

    //Glide
    implementation(Glide.glide)

}