plugins {
    id("android.base.config")
}

android {
    namespace = "com.example.registration_login_api"
}

dependencies {
    testImplementation(libs.junit)
    //Modules
    implementation(project(":core"))
}