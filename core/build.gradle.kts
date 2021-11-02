
import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
}
val properties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}
android {
    compileSdk =BuildConfig.COMPILE_SDK
    buildToolsVersion =BuildConfig.BUILD_TOOLS

    defaultConfig {
        minSdk = BuildConfig.MIN_SDK
        targetSdk =BuildConfig.TARGET_SDK
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

        it.buildConfigField("String", "MARVEL_API_KEY_PUBLIC",properties.getProperty("marvel.key.public"))
        it.buildConfigField("String", "MARVEL_API_KEY_PRIVATE",properties.getProperty("marvel.key.private"))
        //it.buildConfigStringField("MARVEL_API_BASE_URL", "gateway.marvel.com")
        //it.buildConfigStringField("CHARACTER_LIST_ENDPOINT", "/v1/public/characters")
        //it.buildConfigStringField("CHARACTER_ENDPOINT", "/v1/public/characters")
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvm.get()
    }
}

dependencies {

    //AndroidX
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.viewmodel)

    //Navigation
    implementation (libs.androidx.navigation.fragment)

    //Koin
    implementation(libs.koin.core)

    //Network Libs
    implementation (libs.ktor.cio)
    implementation (libs.ktor.log)
    implementation (libs.ktor.serialization)
    implementation (libs.slf4j)

    //UI
    implementation(libs.coil)

    testImplementation(libs.bundles.test.suite)
    testImplementation(libs.testing.ktor)

}