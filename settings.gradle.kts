pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "github-repositories"

include(":app")
include(":presentation")
include(":domain")
include(":data")

project(":app").projectDir = File(rootDir, "modules/app")
project(":presentation").projectDir = File(rootDir, "modules/presentation")
project(":domain").projectDir = File(rootDir, "modules/domain")
project(":data").projectDir = File(rootDir, "modules/data")
