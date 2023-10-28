plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.dagger.hilt)
  alias(libs.plugins.kotlin.ksp)
  alias(libs.plugins.androidx.navigation.safearg)
}

val compileSdkVer: Int by rootProject.extra
val targetSdkVer: Int by rootProject.extra
val minimumSdkVer: Int by rootProject.extra
val versionNameConfig: String by rootProject.extra
val versionCodeConfig: Int by rootProject.extra

android {
  namespace = "com.mta.newsapp"
  compileSdk = compileSdkVer

  defaultConfig {
    applicationId = "com.mta.newsapp"
    minSdk = minimumSdkVer
    targetSdk = targetSdkVer
    versionCode = versionCodeConfig
    versionName = versionNameConfig

    setProperty("archivesBaseName", "News -$versionNameConfig")

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildFeatures {
      viewBinding = true
      buildConfig = true
    }
  }

  buildTypes {

    debug {
      isMinifyEnabled = false
      isDebuggable = true
    }

    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = "17"
  }

}

dependencies {

  coreLibraryDesugaring(libs.desugar.jdk.libs)

  implementation(libs.androidx.constraintLayout)
  implementation(libs.androidx.core)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.recyclerview)

  implementation(libs.androidx.activity)
  implementation(libs.androidx.fragment)

  implementation(libs.androidx.viewpager2)

  //coroutine
  implementation(libs.coroutine.core)
  implementation(libs.coroutine.android)

  implementation(libs.androidx.lifecycle.viewModel)
  implementation(libs.androidx.lifecycle.liveData)
  implementation(libs.androidx.lifecycle.extensions)
  implementation(libs.androidx.lifecycle.java8)
  implementation(libs.androidx.lifecycle.service)

  //navigation
  implementation(libs.androidx.navigation.fragment)
  implementation(libs.androidx.navigation.ui)

  //coil
  implementation(libs.coil)

  //networking
  implementation(libs.okhttp.client)
  implementation(libs.okhttp.logger)
  implementation(libs.retrofit.main)
  implementation(libs.retrofit.converter.moshi)
  implementation(libs.moshi.core)
  implementation(libs.moshi.adapters)
  implementation(libs.moshi.kotlin)
  ksp(libs.moshi.codeGen)

  //timber
  implementation(libs.timber)

  //pretty time
  implementation(libs.prettytime)

  //hilt
  implementation(libs.dagger.hilt.android)
  ksp(libs.dagger.hilt.compiler)
  ksp(libs.dagger.hilt.android.compiler)
  androidTestImplementation(libs.dagger.hilt.android.testing)

  //room
  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  ksp(libs.room.compiler)

  testImplementation(libs.junit.junit4)
  androidTestImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.espresso)
}