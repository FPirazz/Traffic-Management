plugins {
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.1.0"
    id("java")
    jacoco
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

allprojects {
    group = "org.SPE_SAP"
    version = "0.0.1-SNAPSHOT"


    repositories {
        mavenCentral()
    }

}

dependencies {
    implementation("org.json:json:20231013")
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}