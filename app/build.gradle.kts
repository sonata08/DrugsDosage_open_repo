plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinKsp)
}

android {
    namespace = "sonata.open.drugsdosage"
    compileSdk = 35

    defaultConfig {
        applicationId = "sonata.open.drugsdosage"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "GOOGLE_PLAY_URL", "\"https://play.google.com/store/apps/details?id=" + applicationId + "\"")
        buildConfigField("String", "GOOGLE_MARKET_URL", "\"market://details?id=" + applicationId + "\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
    sourceSets {
        // Adds exported schema location as test app assets.
        getByName("androidTest").assets.srcDir("$projectDir/schemas")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
            // AD_UNIT_ID should be replaces with the real id
            buildConfigField("String", "AD_UNIT_ID", "\"ca-app-pub-3940256099942544/6300978111\"")
        }
        debug {
            manifestPlaceholders["crashlyticsCollectionEnabled"] = false
            buildConfigField("String", "AD_UNIT_ID", "\"ca-app-pub-3940256099942544/6300978111\"")
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
//        viewBinding = true
        compose = true
        buildConfig = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
//    }
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
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.window)
    implementation(libs.material)

    // Local Unit Tests
    testImplementation(libs.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)

    // Instrumented Unit Tests
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.google.truth)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // dataStore
    implementation(libs.androidx.datastore)

    // hilt
    implementation(libs.dagger.hilt)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation)

    // room
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    // in-app updates
    implementation(libs.play.review.ktx)

    // AdMob
    implementation(libs.play.services.ads)
}