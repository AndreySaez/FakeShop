plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.fakeshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fakeshop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.datastore.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //dagger
    kapt(libs.dagger.compiler)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)

    //viewModel
    implementation(libs.androidx.fragment.ktx.v180)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //network
    implementation(libs.retrofit)

    //Modules
    //Core
    implementation(project(":core"))

    //appLauncher
    implementation(project(":app-launcher"))

    //registration and login
    implementation(project(":registration-login:impl"))
    implementation(project(":registration-login:api"))

    //productsList
    implementation(project(":products-list:impl"))
    implementation(project(":products-list:api"))

    //productDetails
    implementation(project(":product-details:api"))
    implementation(project(":product-details:impl"))
}