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
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")
    implementation("mysql:mysql-connector-java:8.0.30")
}
