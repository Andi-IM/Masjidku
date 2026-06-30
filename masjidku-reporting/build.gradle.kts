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
    implementation(project(":masjidku-auth-client"))
    implementation(project(":masjidku-reporting-client"))
    implementation(libs.openpdf) {
        exclude(group = "xml-apis", module = "xml-apis")
        exclude(group = "xml-apis", module = "xml-apis-ext")
    }
    implementation(libs.jasperreports) {
        exclude(group = "com.lowagie", module = "itext")
        exclude(group = "xml-apis", module = "xml-apis")
        exclude(group = "xml-apis", module = "xml-apis-ext")
    }
    implementation(libs.jasperreports.pdf) {
        exclude(group = "xml-apis", module = "xml-apis")
        exclude(group = "xml-apis", module = "xml-apis-ext")
    }
    implementation(libs.slf4j)


}
