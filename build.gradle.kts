plugins {
    id("org.danilopianini.git-sensitive-semantic-versioning") version "0.1.0"
    id("java")
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
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

tasks.register("runAll") {
    group = "run"
    description = "Runs all the necessary component for the application"
    dependsOn(":userContext:runUserApplication")
    dependsOn(":tcm_frontend:npmRunVite")
    dependsOn(":intersectionAggregate:runAgents")
}

tasks.register("cleanAll") {
    group = "build"
    dependsOn(":userContext:clean")
    dependsOn(":tcm_frontend:npmClean")
    dependsOn(":intersectionAggregate:clean")
}

tasks.register("buildAll") {
    group = "build"
    dependsOn(":userContext:build")
    dependsOn(":tcm_frontend:npmBuildProject")
    dependsOn(":intersectionAggregate:build")
}

tasks.register("cleanAndBuildAll") {
    group = "build"
    description = "Builds and cleans all the subproject of the application"
    dependsOn("cleanAll")
    dependsOn("buildAll")
}


