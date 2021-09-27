import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_SERIALIZATION) version  Versions.kotlinSerialization
}
val properties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
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


    buildTypes.forEach {

        it.buildConfigField("String", "MARVEL_API_KEY_PUBLIC",properties.getProperty("marvel.key.public"))
        it.buildConfigField("String", "MARVEL_API_KEY_PRIVATE",properties.getProperty("marvel.key.private"))
        //it.buildConfigStringField("MARVEL_API_BASE_URL", "gateway.marvel.com")
        //it.buildConfigStringField("CHARACTER_LIST_ENDPOINT", "/v1/public/characters")
        //it.buildConfigStringField("CHARACTER_ENDPOINT", "/v1/public/characters")
    }

    kotlinOptions {
        jvmTarget = Versions.jvm
    }
}

dependencies {

    //Kotlin
    implementation(Dependencies.Kotlin.stdlib)

    //AndroidX
    implementation(Dependencies.AndroidX.ktx)

    //Koin
    implementation(Dependencies.DI.koin)

    //Network Libs
    implementation (Dependencies.Network.ktor)
    implementation (Dependencies.Network.ktorCio)
    implementation (Dependencies.Network.ktorLog)
    implementation (Dependencies.Logging.slf4j)
    implementation (Dependencies.Network.ktorSerialization)

    //UI
    implementation(Dependencies.Images.coil)

}