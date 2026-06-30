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
    // Database
    implementation(libs.mysql.connector)
    implementation(libs.sqlite.jdbc)
    implementation(libs.hibernate.core)
    implementation(libs.jboss.logging)
    implementation(libs.jakarta.transaction)
    implementation(libs.jakarta.interceptor)
    implementation(libs.jakarta.cdi)

    // Submodules
    implementation(project(":masjidku-accounting-client"))
    implementation(project(":masjidku-events-client"))
    implementation(project(":masjidku-reporting-client"))
    implementation(project(":masjidku-auth-client"))
    
    // Service implementations (runtime)
    implementation(project(":masjidku-common"))
    implementation(project(":masjidku-accounting"))
    implementation(project(":masjidku-events"))
    implementation(project(":masjidku-reporting"))
    implementation(project(":masjidku-auth"))
    
    // XML Bind
    implementation(libs.jaxb.api)
    implementation(libs.jetbrains.annotations)

    // Hash Security and other tools
    implementation(libs.guava)

    // Utilities
    implementation(libs.slf4j)
    runtimeOnly(libs.logback)
    implementation(libs.validatorfx)

    // Unit Test
    testImplementation(libs.junit)
    testImplementation(libs.archunit.junit5)

    // Dagger 2 DI
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
}

javafx {
    version = libs.versions.javafx.get()
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainModule.set("main")
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



tasks.register<JavaExec>("runTestJasper") {
    description = ""
    mainClass.set("org.masjidku.TestJasper")
    classpath = sourceSets["main"].runtimeClasspath
}


tasks.register<JavaExec>("compileJasper") {
    description = ""
    mainClass.set("org.masjidku.Compiler")
    classpath = sourceSets["main"].runtimeClasspath
}


dependencies {

}


tasks.named<JavaExec>("run") {
    modularity.inferModulePath.set(true)
}





tasks.named<JavaExec>("run") {
    doFirst {
        jvmArgs = listOf(
            "--module-path", classpath.asPath,
            "--add-modules", "ALL-MODULE-PATH",
            "--module", "main/org.masjidku.MainApp",
            "--enable-native-access=javafx.graphics",
            "--sun-misc-unsafe-memory-access=allow"
        )
        classpath = files()
    }
}
