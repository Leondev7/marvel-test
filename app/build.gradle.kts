val appVersionName: String by rootProject.extra
val appVersionCode : String by rootProject.extra

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
        versionCode = appVersionCode.toInt()
        versionName = appVersionName
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
        jvmTarget = libs.versions.jvm.get()
    }

}

dependencies {

    //Modules
    implementation( project(BuildModules.CORE))
    implementation( project(BuildModules.Features.CHARACTERS))

    //Dependency Injection
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    //Android
    implementation (libs.androidx.ktx)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.androidx.appcompat)

    //Hilt

    //Navigation
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)



}
