plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.chaintech.candlechartcompose"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.compose.material3:material3:1.2.0-beta02")
    implementation("androidx.compose.foundation:foundation:1.6.0-beta03")
    implementation("androidx.compose.ui:ui:1.6.0-beta03")
    implementation("androidx.compose.material3:material3")
}

afterEvaluate {
    android.libraryVariants.forEach { variant ->
        publishing {
            publications.create(variant.name, MavenPublication::class) {
                from(components.findByName(variant.name))
                groupId = "com.chaintech"
                artifactId = "candlechartcompose"
                version = "1.0.0"
            }
        }
    }
}

//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            from(components["release"])
//            groupId = "com.example"
//            artifactId = "mylibrary"
//            version = "1.0.0"
//        }
//    }
//}