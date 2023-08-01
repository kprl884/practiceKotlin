plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.trendyol.hiring"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.trendyol.hiring"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "TMDB_API_KEY", "\"6972ceddbb152db738f2710751cc5df6\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    // TODO enable the dependencies you want to use

    // region androidx
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.6.0")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    // endregion

    // region androidx lifecycle
    val lifecycleVersion = "2.6.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
    // endregion

    implementation("com.google.android.material:material:1.9.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

    // dependency injection
    val daggerVersion = "2.46.1"
    implementation("com.google.dagger:hilt-android:$daggerVersion")
    kapt("com.google.dagger:hilt-compiler:$daggerVersion")
    //

    // region networking
    val retrofitVersion = "2.9.0"
    val okhttpVersion = "4.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.github.bumptech.glide:glide:4.15.1")

//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
//    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    // endregion

    // region test
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("junit:junit:4.13.2")
    // endregion

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")

    // region androidTest
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    // endregion
}
