pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DerintMobile"
include(":app")
include(":news")
include(":core")
include(":news:ui")
include(":news:presentation")
include(":news:data")
include(":core:network")
include(":news:remote")
include(":news:cache")
include(":news:domain")
