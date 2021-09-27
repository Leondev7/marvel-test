plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.SAFEARGS)
    id(BuildPlugins.KOTLIN_KAPT)
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

    kotlinOptions {
        jvmTarget = Versions.jvm
    }

}

dependencies {

    //Modules
    implementation( project(BuildModules.CORE))
    implementation( project(BuildModules.Features.CHARACTERS))

    //Dependency Injection
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinAndroid)

    //Stdlib
    implementation( Dependencies.Kotlin.stdlib)
    //Android
    implementation (Dependencies.AndroidX.ktx)
    implementation (Dependencies.AndroidX.constraintLayout)
    implementation (Dependencies.AndroidX.appcompat)

    //Hilt

    //Navigation
    implementation (Dependencies.AndroidX.navigation)
    implementation (Dependencies.AndroidX.navigationUI)



}
