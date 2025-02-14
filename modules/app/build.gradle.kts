plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.murta.github_repositories"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.murta.github_repositories"
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
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.navigation.compose)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}