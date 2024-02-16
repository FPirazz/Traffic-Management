import com.github.gradle.node.npm.proxy.ProxySettings
import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
import org.codehaus.groovy.ast.tools.GeneralUtils.args
import org.gradle.launcher.daemon.protocol.Build

plugins {
    java
    id("com.github.node-gradle.node") version "7.0.2"
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

dependencies {
    implementation("commons-io:commons-io:2.6")
}

node {
    // Version of node to use.
    version = "18.15.0"

    // Version of npm to use.
    npmVersion = ""

    // Base URL for fetching node distributions (change if you have a mirror).
    distBaseUrl = "https://nodejs.org/dist"

    // If true, it will download node using above parameters.
    // If false, it will try to use globally installed node.
    download = true

    // Set the work directory for unpacking node
    workDir = file("${rootProject.projectDir}/.gradle/nodejs")

    // Set the work directory for NPM
    npmWorkDir = file("${rootProject.projectDir}/.gradle/npm")

    // The Node.js project directory location
    // This is where the package.json file and node_modules directory are located
    // By default it is at the root of the current project
    nodeProjectDir = file("${rootProject.projectDir}")

    // Whether the plugin automatically should add the proxy configuration to npm and yarn commands
    // according the proxy configuration defined for Gradle
    // Disable this option if you want to configure the proxy for npm or yarn on your own
    // (in the .npmrc file for instance)
    nodeProxySettings = ProxySettings.SMART
}

tasks.register<Delete>("npmClean") {
    doFirst {
        delete("dist")
        delete("build")
    }
}

tasks.register<NpmTask>("npmRunBuild") {
    workingDir = file(projectDir.path)
    npmCommand.add("run")
    npmCommand.add("build")
}

tasks.register<NpmTask>("npmRunProject") {
    workingDir = file(projectDir.path)
    npmCommand.add("run")
    npmCommand.add("dev")
}

tasks.register<NpmTask>("npmInstallProject") {
    group = "npm"
    description = "Installs the package.json"
    workingDir = file(projectDir.path)
    npmCommand.add("install")
}

tasks.register("npmBuildProject") {
    group = "npm"
    description = "Runs the build script for the Vue project"
    dependsOn(tasks.getByName("npmInstallProject"))
    dependsOn(tasks.getByName("npmClean"))
    dependsOn(tasks.getByName("npmRunBuild"))
}

tasks.register("npmRunVite") {
    group = "npm"
    description = "Runs the Vue project via Vite"
    dependsOn(tasks.getByName("npmRunProject"))
}




