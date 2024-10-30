package com.example.conventionplugin.base

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

//  Нужен  AndroidPlugin, DaggerPlugin, FeatureModulePlugin, AppPlugin (под вопросом)
class AndroidPlugin: Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.jetbrainsKotlinAndroid.get().pluginId)
            apply("kotlin-kapt") // todo catalog
            apply("kotlin-parcelize") // todo catalog
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

        //Dagger2
        dependencies.add("api", libs.dagger.asProvider().get())
        dependencies.add("kapt", libs.dagger.compiler.get())
    }
}