plugins {
    id("feature.config")
}

android {
    namespace = "com.example.coremodule"
}

dependencies {
    testImplementation(libs.junit)

    //network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
}