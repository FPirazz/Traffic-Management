plugins {
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.1.0"
    id("java")
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(19)
    }
    sourceCompatibility = JavaVersion.VERSION_19
}

tasks.wrapper {
    gradleVersion = "8.2"
}

allprojects {
    group = "org.SPE_SAP"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
    }
}
