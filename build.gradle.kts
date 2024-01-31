plugins {
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.1.0"
    id("java")
    jacoco
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    group = "org.SPE_SAP"
    version = "0.0.1-SNAPSHOT"


    repositories {
        mavenCentral()
    }

}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}