plugins {
    id("feature.config")
}

android {
    namespace = "com.example.productdetailsapi"
}

dependencies {
    implementation(libs.material)
    testImplementation(libs.junit)

    //Modules
    implementation(project(":core"))
}