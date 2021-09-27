object Dependencies {

    object AndroidX{
        // Kotlin extensions for android
        const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
        // AppCompat library
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        // AppCompat library
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        // Android lifecycle runtime
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        //Android lifecycle livedata
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        //Android lifecycle viewmodel
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        //Navigation
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        //Navigation ui
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }
    object Google{
        // Android Material Design Components
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Network {
        val ktor = "io.ktor:ktor-client-core:${Versions.ktor}"
        val ktorCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
        val ktorLog = "io.ktor:ktor-client-logging:${Versions.ktor}"
        val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    }

    object Logging{
        const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    }

    object DI{
        // Koin for Kotlin Multiplatform
        const val koin = "io.insert-koin:koin-core:${Versions.koin}"
        // Koin Test for Kotlin Multiplatform
        const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
        // Koin main features for Android (Scope,ViewModel ...)
        const val koinAndroid ="io.insert-koin:koin-android:${Versions.koin}"
    }

    object Images{
        const val coil = "io.coil-kt:coil:${Versions.coil}"
    }

    object Kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }


    object BuildPlugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val navigation =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    }
}