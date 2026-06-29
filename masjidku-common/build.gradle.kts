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
    implementation("org.slf4j:slf4j-api:2.0.12")
}
