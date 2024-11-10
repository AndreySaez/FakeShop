plugins {
    id("android.base.config")
}

android {
    namespace = "com.example.productslistapi"
}

dependencies {
    //Modules
    implementation(project(":core"))
}