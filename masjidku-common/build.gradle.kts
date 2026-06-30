plugins {
    java
}

java {
    modularity.inferModulePath.set(true)
}

repositories {
    mavenCentral()
}

tasks.named<JavaCompile>("compileJava") {
    options.encoding = "UTF-8"
    doFirst {
        options.compilerArgs.addAll(listOf("--module-path", classpath.asPath))
        classpath = files()
    }
}

dependencies {
    // ORM dependencies
    implementation(libs.hibernate.core)
    implementation(libs.hibernate.community.dialects)
    implementation(libs.hibernate.hikaricp)
    implementation(libs.hikaricp)

    // Dagger 2 DI
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
}