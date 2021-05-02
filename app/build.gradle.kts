plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id( "kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(BuildConfig.COMPILE_SDK_VERSION)
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
    defaultConfig {
        applicationId = BuildConfig.APPLICATION_ID
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

}

dependencies {

    implementation(project(":core"))
    implementation(project(":features:characters"))
    //Kotlin
    implementation (Kotlin.stdlib)
    //Android
    implementation (Android.coreKTX)
    implementation (Android.materialX)
    implementation (Android.appcompatX)
    implementation (Android.constraintLayoutX)
    implementation (Android.livedataX)
    implementation (Android.viewmodelX)
    implementation (Android.fragmentX)

    //Hilt
    implementation (Hilt.hilt)
    kapt(Hilt.compiler)

    //Navigation
    implementation(Navigation.navFragment)
    implementation(Navigation.navUI)



}
