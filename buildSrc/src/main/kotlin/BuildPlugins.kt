/**
 * Configuration of all gradle build plugins
 */
object BuildPlugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlin.plugin.serialization"

    const val KOTLIN_MULTIPLATFORM = "multiplatform"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"

    const val HILT = "dagger.hilt.android.plugin"
    const val SAFEARGS = "androidx.navigation.safeargs.kotlin"
    const val GPP = "com.github.triplet.play"


}