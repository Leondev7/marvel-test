
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":features:characters",":core",":app")
rootProject.name = "MarvelTest"
enableFeaturePreview("VERSION_CATALOGS")