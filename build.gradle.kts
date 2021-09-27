// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
    dependencies {
        classpath(Dependencies.BuildPlugins.gradle)
        classpath(Dependencies.BuildPlugins.kotlin)
        classpath(Dependencies.BuildPlugins.navigation)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}
