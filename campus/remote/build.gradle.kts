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
    implementation(project(":campus:domain"))
    implementation(project(":campus:data"))

    implementation(libs.bundles.javax)
    implementation(libs.bundles.retrofit)

}