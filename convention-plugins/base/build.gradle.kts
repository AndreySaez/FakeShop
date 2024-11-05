import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    `kotlin-dsl`
}
group = "com.example.conventionplugin.base"

dependencies {
    implementation(libs.gradleplugin.android)
    implementation(libs.gradleplugin.compose)
    implementation(libs.gradleplugin.kotlin)
    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}


private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.javaConvention.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}
tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}

gradlePlugin {
    plugins {
        register("android.base.config") {
            id = "android.base.config"
            implementationClass = "com.example.conventionplugin.base.AndroidPlugin"
        }
        register("dagger.config") {
            id = "dagger.config"
            implementationClass = "com.example.conventionplugin.dagger.DaggerPlugin"
        }
        register("feature.config"){
            id = "feature.config"
            implementationClass = "com.example.conventionplugin.featureModule.FeatureModulePlugin"
        }
        register("compose.config"){
            id = "compose.config"
            implementationClass = "com.example.conventionplugin.compose.ComposePlugin"
        }
    }
}
