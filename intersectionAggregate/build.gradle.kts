import org.apache.tools.ant.taskdefs.Java

apply(".jcm-deps.gradle")

plugins {
    id("java")
    id("maven-publish")
    application
}

sourceSets {
    main {
        java {
            srcDirs("src/agt", "src/env", "src/org")
        }
        resources {
            srcDirs("src/resources")
        }
    }

}

application {
    mainClass = "jacamo.infra.JaCaMoLauncher"
}

repositories {
    maven { url = uri("https://raw.githubusercontent.com/jacamo-lang/mvn-repo/master") }
    maven { url = uri( "https://repo.gradle.org/gradle/libs-releases") }
    maven { url = uri( "https://jitpack.io") }

    mavenCentral()
}

dependencies {
    implementation("org.jacamo:jacamo:1.2")
    implementation("org.jacamo:cartago:3.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.9.8")
}


task<JavaExec>("runAgents") {
    group = "JaCaMo"
    description = "Runs the JaCaMo application"
    mainClass = "jacamo.infra.JaCaMoLauncher"
    args("intersection.jcm")

    doFirst {
        mkdir("./log")
    }
    dependsOn("classes")
    classpath(configurations.runtimeClasspath)
}


