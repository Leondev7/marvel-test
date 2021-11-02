plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.SAFEARGS)
}
android {
    compileSdk =BuildConfig.COMPILE_SDK
    buildToolsVersion = BuildConfig.BUILD_TOOLS

    defaultConfig {
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK
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
    //MOdules
    implementation(project(BuildModules.CORE))

    //Android
    implementation(libs.androidx.ktx)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)

    //Lifecycle
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.livedata)

    //Android Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //Dependency Injection
    implementation(libs.koin.android)

    testImplementation(libs.bundles.test.suite)
    testImplementation(libs.testing.androidx.arch)

}