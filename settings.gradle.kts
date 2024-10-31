pluginManagement {
    includeBuild("convention-plugins/base")
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
fun findModules(dir: File, parentName: String = ""): List<String> {
    val modules = mutableListOf<String>()
    dir.listFiles()?.forEach { file ->
        if (file.isDirectory) {
            if (File(file, "build.gradle.kts").exists()) {
                val moduleName = if (parentName.isEmpty()) {
                    file.name
                } else {
                    "$parentName:${file.name}"
                }
                modules.add(moduleName)
            }
            val modulesName = findModules(
                file,
                if (parentName.isEmpty()) file.name else "$parentName:${file.name}"
            )
            modules.addAll(modulesName)
        }
    }
    return modules
}

val rootDir = file(".")
val modules = findModules(rootDir)
val blackList = setOf("convention-plugins:base")
modules.forEach { module ->
    if (module !in blackList) {
        include(":$module")
    }
}

rootProject.name = "FakeShop"

