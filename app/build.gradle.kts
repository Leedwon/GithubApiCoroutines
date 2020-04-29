import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.statistics.metrics.StringMetrics

val koinVersion = "2.1.3"

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.2"

    defaultConfig {
        applicationId = "com.example.kotlingradleexample"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }

        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0-alpha05")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0-alpha05")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.4")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("org.koin:koin-androidx-scope:$koinVersion")
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")
    implementation("org.koin:koin-androidx-fragment:$koinVersion")
    implementation("com.squareup.okhttp3:okhttp:4.0.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.0.1")
    implementation("com.squareup.retrofit2:retrofit:2.7.1")
    implementation("com.google.android.material:material:1.2.0-alpha05")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.gojuno.koptional:koptional:1.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0-alpha01")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.0-beta4")
    implementation(project(":network"))
    implementation(project(":model"))
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:3.1.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.4")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation(kotlin("reflect"))
}