plugins {
    java
    id("org.openjfx.javafxplugin")
}

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

javafx {
    version = "21.0.6"
    modules = listOf("javafx.base")
}

dependencies {
    implementation(project(":masjidku-common"))
    implementation(project(":masjidku-accounting-client"))
}
