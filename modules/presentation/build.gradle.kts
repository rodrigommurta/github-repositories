plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.murta.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    lint {
        abortOnError = false
        warningsAsErrors = true
        checkReleaseBuilds = false
        disable.add("UnusedResources")
        enable.add("ObsoleteSdkInt")
        lintConfig = file("lint.xml")
    }
}

detekt {
    config = files("$rootDir/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = true
}

tasks.named("check") {
    dependsOn("detekt")
}

dependencies {
    implementation(project(":data"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    debugImplementation(libs.ui.tooling)

    testImplementation(libs.junit)
    testImplementation(libs.core.testing)
    testImplementation(libs.android.core.testing)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)

    debugImplementation(libs.androidx.ui.test.manifest)
}