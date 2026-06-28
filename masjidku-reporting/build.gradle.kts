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
    implementation(project(":masjidku-common"))
    implementation(project(":masjidku-reporting-client"))
    implementation(libs.openpdf)
    implementation(libs.jasperreports) {
        exclude(group = "com.lowagie", module = "itext")
        exclude(group = "xml-apis", module = "xml-apis")
        exclude(group = "xml-apis", module = "xml-apis-ext")
    }
    implementation("org.slf4j:slf4j-api:2.0.12")
}
