plugins {
    id("feature.config")
    id("compose.config")
}

android {
    namespace = "com.example.registartion_login"

    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Modules
    implementation(project(":core"))
    implementation(project(":products-list:api"))
    implementation(project(":registration-login:api"))

    //network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //viewModel
    implementation(libs.androidx.fragment.ktx.v180)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //WorkManager
    // Kotlin + coroutines
    implementation(libs.androidx.work.runtime.ktx)
    // optional - Test helpers
    androidTestImplementation(libs.androidx.work.testing)

    //JUnit5
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    //Mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}