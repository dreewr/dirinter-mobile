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

rootProject.name = "DirinterMobile"
include(":app")

include(":core")
include(":core:ui")
include(":core:network")
include(":core:presentation")

include(":news")
include(":news:ui")
include(":news:presentation")
include(":news:data")
include(":core:network")
include(":news:remote")
include(":news:cache")
include(":news:domain")

include(":login")
include(":login:ui")
include(":login:presentation")
include(":login:data")
include(":login:remote")
include(":login:cache")
include(":login:domain")
