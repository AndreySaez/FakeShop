package com.example.conventionplugin.featureModule

import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.example.conventionplugin.base.AndroidPlugin")
            apply("com.example.conventionplugin.dagger.DaggerPlugin")
        }
    }
}