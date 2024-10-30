pluginManagement {
    includeBuild("convention-plugins/base")
    repositories {
        google {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
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

rootProject.name = "FakeShop"

// хочу чтобы модулу сами сюда добавлялись
include(":app")
include(":core")
include(":productDetails")
include(":registration_login")
include(":productsList")
