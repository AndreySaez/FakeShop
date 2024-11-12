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
    implementation(project(":product-details:api"))

    //coil
    implementation(libs.coil)
}