apply(".jcm-deps.gradle")

plugins {
    id("java")
    id("maven-publish")
    application
}

sourceSets {
    main {
        java {
            srcDirs("src/env", "src/agt", "src/org")
        }
        resources {
            srcDirs("src/resources")
        }
    }
}

repositories {
    maven { url = uri("https://raw.githubusercontent.com/jacamo-lang/mvn-repo/master") }
    maven { url = uri( "https://repo.gradle.org/gradle/libs-releases") }
    maven { url = uri( "https://jitpack.io") }
    flatDir { dirs("lib") }
}

dependencies {
    implementation("org.jacamo:jacamo:1.2.2")
    implementation("org.jacamo:cartago:3.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.9.8")
    implementation("org.jacamo:jacamo-rest:0.7")
    implementation("org.json:json:20231013")
}

task<JavaExec>("runAgents") {
    group = "JaCaMo"
    description = "Runs the JaCaMo application"
    dependsOn("classes")
    mainClass = "jacamo.infra.JaCaMoLauncher"
    args("intersection.jcm")

    doFirst {
        mkdir("./log")
    }

    classpath(sourceSets.main.get().runtimeClasspath)
}