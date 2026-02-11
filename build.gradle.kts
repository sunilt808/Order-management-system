plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.org.testng.testng)
}

group = "com.company.oms"
version = "0.0.1-SNAPSHOT"
description = "order-management-system"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.named<Test>("test") {
    useTestNG()
}
