plugins {
    java
    application
    alias(libs.plugins.javafx)
    alias(libs.plugins.jlink)
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
    // Database
    implementation(libs.mysql.connector)

    // Submodules
    runtimeOnly(project(":masjidku-accounting"))
    implementation(project(":masjidku-accounting-client"))
    implementation(project(":masjidku-events"))
    implementation(project(":masjidku-events-client"))
    implementation(project(":masjidku-reporting"))
    implementation(project(":masjidku-reporting-client"))
    
    // XML Bind
    implementation(libs.jaxb.api)
    implementation(libs.jetbrains.annotations)

    // Hash Security and other tools
    implementation(libs.guava)

    // Unit Test
    testImplementation(libs.junit)
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
        installerOutputDir = file("$buildDir/installers")
        installerOptions = listOf(
                "--vendor", "Group 5",
                "--win-per-user-install", "--win-dir-chooser",
                "--win-menu", "--win-shortcut"
        )
    }
}
