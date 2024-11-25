plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("com.google.gms.google-services")
}

android {
    namespace = "uk.ac.tees.mad.w9624019.cinimaapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "uk.ac.tees.mad.w9624019.cinimaapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val TMDB_API_KEY = "8bc098faec5fcf7e3076859d91f4a5ab"
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"

            )
            buildConfigField("String", "TMDB_API_KEY", "\"${TMDB_API_KEY}\"")
        }
        debug {
            buildConfigField("String", "TMDB_API_KEY", "\"${TMDB_API_KEY}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//    dagger-hilt
    implementation(libs.hilt.android)
    ksp("androidx.hilt:hilt-compiler:1.2.0")
    ksp("com.google.dagger:hilt-compiler:2.51.1")


//    networking
    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.logging.interceptor)

//    navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

//    room
    implementation(libs.room.runtime)
    implementation("androidx.room:room-rxjava3:2.6.0")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.room.ktx)

    testImplementation ("io.mockk:mockk:1.13.5")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")

    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    //navigation
    implementation("androidx.navigation:navigation-compose:2.4.0")

    //paging
    implementation("androidx.paging:paging-runtime:3.2.1")
    implementation("androidx.paging:paging-compose:3.2.1")

    //retrofit & rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.0.13")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")



    //glide
    implementation("com.github.bumptech.glide:glide:4.15.0")
    //coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("androidx.compose.runtime:runtime-livedata:1.0.0")

    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")


}

