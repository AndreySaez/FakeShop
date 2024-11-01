plugins {
    id("android.base.config")
}

android {
    namespace = "com.example.productdetails"
}

dependencies {
    implementation(libs.material)
    testImplementation(libs.junit)

    //Modules
    implementation(project(":core"))

    //coil
    implementation(libs.coil)
}