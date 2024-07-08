plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.akzhey.harrypotter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.akzhey.harrypotter"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Hilt
    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.android.compiler)
    annotationProcessor(libs.hilt.compiler)

    //Glide
    implementation (libs.glide)

    // Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

}