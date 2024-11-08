plugins {
    id("feature.config")
}

android {
    namespace = "com.example.productdetails"
}

dependencies {
    implementation(libs.material)
    testImplementation(libs.junit)

    //Modules
    implementation(project(":core"))
    implementation(project(":productDetailsApi"))

    //coil
    implementation(libs.coil)
}