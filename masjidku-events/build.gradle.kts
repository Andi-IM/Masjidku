plugins {
    java
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



dependencies {
    implementation(project(":masjidku-common"))
    implementation(project(":masjidku-auth-client"))
    implementation(project(":masjidku-events-client"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.slf4j)
    implementation(libs.hibernate.core)
    implementation(libs.hibernate.community.dialects)
    implementation(libs.hibernate.hikaricp)
    implementation(libs.hikaricp)

    // Dagger 2 DI
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
}
