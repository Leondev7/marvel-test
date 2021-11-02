val appVersionName: String by project
val appVersionCode : String by project

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs") as org.gradle.accessors.dm.LibrariesForLibs
        classpath(libs.buildPlugin.gradle)
        classpath(libs.buildPlugin.kotlin)
        classpath(libs.buildPlugin.navigation)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
