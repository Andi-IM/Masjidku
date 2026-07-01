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
    // Global excludes to prevent jdeps from finding them and requiring them
    configurations.all {
        exclude(group = "jakarta.enterprise", module = "jakarta.cdi-api")
        exclude(group = "jakarta.transaction", module = "jakarta.transaction-api")
    }
    
    // implementation
    implementation(libs.mysql.connector) {
        exclude(group = "com.google.protobuf", module = "protobuf-java")
    }
    implementation(libs.sqlite.jdbc)
    implementation(libs.hibernate.core)
    implementation(libs.jakarta.interceptor)
    implementation(project(":masjidku-accounting-client"))
    implementation(project(":masjidku-events-client"))
    implementation(project(":masjidku-reporting-client"))
    implementation(project(":masjidku-auth-client"))
    implementation(project(":masjidku-common"))
    implementation(project(":masjidku-accounting"))
    implementation(project(":masjidku-events"))
    implementation(project(":masjidku-reporting"))
    implementation(project(":masjidku-auth"))
    implementation(libs.jaxb.api)
    implementation(libs.jetbrains.annotations)
    implementation(libs.guava)
    implementation(libs.slf4j)
    implementation(libs.validatorfx)
    implementation(libs.dagger)

    // runtimeOnly
    runtimeOnly(libs.logback)

    // testImplementation
    testImplementation(libs.junit)
    testImplementation(libs.archunit.junit5)

    // annotationProcessor
    annotationProcessor(libs.dagger.compiler)

    // Fix for jlink missing module
    implementation("jakarta.activation:jakarta.activation-api:2.1.3")
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
    forceMerge("HikariCP")
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
    group = "jasper"
    description = "Runs the TestJasper class"
    mainClass.set("org.masjidku.TestJasper")
    classpath = sourceSets["main"].runtimeClasspath
}


tasks.register<JavaExec>("compileJasper") {
    group = "jasper"
    description = "Compiles the Jasper Reports"
    mainClass.set("org.masjidku.Compiler")
    classpath = sourceSets["main"].runtimeClasspath
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