plugins {
    java
    application
    alias(libs.plugins.javafx)
    alias(libs.plugins.jlink)
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

tasks.named<JavaCompile>("compileTestJava") {
    options.encoding = "UTF-8"
}

dependencies {
    implementation(project(":masjidku-common"))
    // Database
    implementation(libs.mysql.connector)
    implementation(libs.sqlite.jdbc)

    // Submodules
    implementation(project(":masjidku-accounting-client"))
    implementation(project(":masjidku-events-client"))
    implementation(project(":masjidku-reporting-client"))
    
    // XML Bind
    implementation(libs.jaxb.api)
    implementation(libs.jetbrains.annotations)

    // Hash Security and other tools
    implementation(libs.guava)

    // Utilities
    implementation("org.slf4j:slf4j-api:2.0.12")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.6")

    // Unit Test
    testImplementation(libs.junit)
    testImplementation(libs.archunit.junit5)
}

javafx {
    version = libs.versions.javafx.get()
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("org.masjidku.MainApp")
    applicationDefaultJvmArgs = listOf(
        "--enable-native-access=javafx.graphics",
        "--sun-misc-unsafe-memory-access=allow"
    )
}

jlink {
    launcher {
        name = "JPackage Demo"
    }

    jpackage {
        installerOutputDir = layout.buildDirectory.dir("installers").get().asFile
        installerOptions = listOf(
                "--vendor", "Group 5",
                "--win-per-user-install", "--win-dir-chooser",
                "--win-menu", "--win-shortcut"
        )
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.compileTestJava {
    modularity.inferModulePath.set(false)
}

