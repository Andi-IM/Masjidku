plugins {
    java
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
    implementation(libs.sqlite.jdbc)
    implementation(libs.mysql.connector)
    implementation(libs.slf4j)
    
    // ORM dependencies
    implementation(libs.hibernate.core)
    implementation(libs.hibernate.community.dialects)
    implementation(libs.hibernate.hikaricp)
    implementation(libs.hikaricp)
    implementation(libs.guava)

    // Dagger 2 DI
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
}
