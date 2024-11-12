plugins {
    id("android.base.config")
}

android {
    namespace = "com.example.api"
}

dependencies {
    testImplementation(libs.junit)
    //Modules
    implementation(project(":core"))
}