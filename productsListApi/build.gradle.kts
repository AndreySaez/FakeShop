plugins {
    id("android.base.config")
}

android {
    namespace = "com.example.productslistapi"
}

dependencies {
    testImplementation(libs.junit)
    //Modules
    implementation(project(":core"))
}