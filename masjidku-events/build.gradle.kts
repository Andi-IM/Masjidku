plugins {
    java
}

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}



dependencies {
    implementation(project(":masjidku-common"))
    implementation(project(":masjidku-events-client"))
    implementation(libs.jetbrains.annotations)
    implementation(libs.slf4j)
    implementation(libs.hibernate.core)
    implementation(libs.hibernate.community.dialects)
    implementation(libs.hibernate.hikaricp)
    implementation(libs.hikaricp)
}
