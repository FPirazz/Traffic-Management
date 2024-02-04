plugins {
    id("java")
    id("maven-publish")
    application
}

application {
    mainClass = "jacamo.infra.JaCaMoLauncher"
    applicationDefaultJvmArgs = listOf("") //TODO INSERT .JCM FILE
}

repositories {
    maven { url = uri("https://raw.githubusercontent.com/jacamo-lang/mvn-repo/master") }
    maven { url = uri( "https://repo.gradle.org/gradle/libs-releases") }
    maven { url = uri( "https://jitpack.io") }

    mavenCentral()
}

dependencies {
    implementation("org.jacamo:jacamo:1.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.9.8")
}


tasks.register("runAgents") {
    group = "JaCaMo"
    description = "Runs the JaCaMo application"
    doFirst {
        mkdir("./log")
    }

    dependsOn("classes")
}