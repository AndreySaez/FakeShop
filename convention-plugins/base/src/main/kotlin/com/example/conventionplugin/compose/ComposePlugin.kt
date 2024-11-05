package com.example.conventionplugin.compose

import com.example.conventionplugin.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val composeBom =
            project.dependencies.platform(libs.androidx.compose.bom)
        extensions.findByType(com.android.build.gradle.BaseExtension::class.java)?.apply {
            buildFeatures.compose = true
            composeOptions.kotlinCompilerExtensionVersion = "1.5.0"
        }
        dependencies.add("implementation", composeBom.get())
        dependencies.add("androidTestImplementation", composeBom.get())
        dependencies.add("implementation", libs.androidx.ui.asProvider().get())
        dependencies.add("implementation", libs.androidx.compiler.get())
        dependencies.add("implementation", libs.androidx.material3.get())
        dependencies.add("implementation", libs.androidx.ui.tooling.preview.get())
        dependencies.add("debugImplementation", libs.androidx.ui.tooling.asProvider().get())
        dependencies.add("implementation", libs.androidx.activity.compose.get())
        dependencies.add("implementation", libs.androidx.lifecycle.viewmodel.compose.get())
    }
}