plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }
android { namespace = "com.soreal.engine"; compileSdk = 36
 defaultConfig { applicationId = "com.soreal.engine"; minSdk = 26; targetSdk = 35; versionCode = 1; versionName = "0.1.0"; externalNativeBuild { cmake { cppFlags += "-std=c++20 -O2 -ffast-math" } } }
 buildTypes { release { isMinifyEnabled = false }; debug { isDebuggable = true } }
 externalNativeBuild { cmake { path = file("src/main/cpp/CMakeLists.txt"); version = "3.31.6" } }
}
dependencies { implementation("androidx.core:core-ktx:1.17.0") }
