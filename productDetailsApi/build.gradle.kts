plugins {
    id("android.base.config")
}

android {
    namespace = "com.example.productdetailsapi"
}

dependencies {
    testImplementation(libs.junit)
    //Modules
    implementation(project(":core"))
}