repositories {
    jcenter()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

object PluginsVersions {
    const val GRADLE_ANDROID = "4.1.0"
}
dependencies{
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
}
