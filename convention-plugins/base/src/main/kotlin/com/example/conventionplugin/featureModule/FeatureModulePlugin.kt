package com.example.conventionplugin.featureModule

import com.example.conventionplugin.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidPlugin.get().pluginId)
            apply(libs.plugins.daggerPlugin.get().pluginId)
        }
    }
}