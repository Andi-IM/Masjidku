plugins {
    java
}

repositories {
    mavenCentral()
}

java {
    modularity.inferModulePath.set(true)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    doFirst {
        options.compilerArgs.addAll(listOf("--module-path", classpath.asPath))
        classpath = files()
    }
}

dependencies {
    implementation(project(":masjidku-reporting-client"))
    implementation(libs.openpdf)
    implementation(libs.jasperreports) {
        exclude(group = "com.lowagie", module = "itext")
    }
}
