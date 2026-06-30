plugins {
    java
    id("org.openjfx.javafxplugin")
}

repositories {
    mavenCentral()
}

java {
    modularity.inferModulePath.set(true)
}

tasks.named<JavaCompile>("compileJava") {
    options.encoding = "UTF-8"
    doFirst {
        options.compilerArgs.addAll(listOf("--module-path", classpath.asPath))
        classpath = files()
    }
}

javafx {
    version = "21.0.6"
    modules = listOf("javafx.base")
}

dependencies {
    implementation(project(":masjidku-common"))
    implementation(project(":masjidku-auth-client"))
    implementation(project(":masjidku-accounting-client"))
    implementation(libs.slf4j)
    implementation(libs.jetbrains.annotations)
    implementation(libs.hibernate.core)
    implementation(libs.hibernate.community.dialects)
    implementation(libs.hibernate.hikaricp)
    implementation(libs.hikaricp)

    // Dagger 2 DI
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
}
