plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.SAFEARGS)
}
android {
    compileSdk = BuildConfig.COMPILE_SDK
    buildToolsVersion = BuildConfig.BUILD_TOOLS

    defaultConfig {
        applicationId = BuildConfig.APPLICATION_ID
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK
        versionCode = BuildConfig.VERSION_CODE
        versionName = BuildConfig.VERSION_NAME
        testInstrumentationRunner = BuildConfig.TEST_INSTRUMENTATION_RUNNER
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

    kotlinOptions {
        jvmTarget = Versions.jvm
    }


}

dependencies {
    //MOdules
    implementation(project(BuildModules.CORE))

    //Kotlin
    implementation(Dependencies.Kotlin.stdlib)

    //Android
    implementation(Dependencies.AndroidX.ktx)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.AndroidX.constraintLayout)

    //Lifecycle
    implementation(Dependencies.AndroidX.viewmodel)
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.livedata)

    //Android Navigation
    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.AndroidX.navigationUI)

    //Dependency Injection
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinAndroid)


}