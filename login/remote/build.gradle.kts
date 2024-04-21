plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":login:domain"))
    implementation(project(":login:data"))

    implementation(libs.bundles.javax)
    implementation(libs.bundles.retrofit)

}