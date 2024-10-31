package com.example.conventionplugin.base

import com.example.conventionplugin.androidConfig
import com.example.conventionplugin.kotlinJvmCompilerOptions
import com.example.conventionplugin.libs
import com.example.conventionplugin.projectJavaVersion
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class AndroidPlugin: Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.jetbrainsKotlinAndroid.get().pluginId)
            apply(libs.plugins.kotlinkapt.get().pluginId)
            apply(libs.plugins.kotlinparcelize.get().pluginId)
        }

        androidConfig {
            compileSdk = libs.versions.compileSdk.get().toInt()
            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }

        kotlinJvmCompilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
        }

        dependencies.add("implementation", libs.androidx.core.ktx.get())
        dependencies.add("implementation", libs.androidx.appcompat.get())
    }
}