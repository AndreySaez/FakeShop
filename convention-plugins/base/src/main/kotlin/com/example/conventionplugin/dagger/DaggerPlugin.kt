package com.example.conventionplugin.dagger

import com.example.conventionplugin.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class DaggerPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        dependencies.add("api", libs.dagger.asProvider().get())
        dependencies.add("kapt", libs.dagger.compiler.get())
    }
}