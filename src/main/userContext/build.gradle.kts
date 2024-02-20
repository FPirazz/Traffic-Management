jacoco {
    toolVersion = "0.8.11"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

repositories {
    mavenCentral()
}

tasks.test {
    onlyIf {
        project.hasProperty("doTests")
    }
}

plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    `jacoco-report-aggregation`
    jacoco
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("com.h2database:h2")
    implementation("jakarta.persistence:jakarta.persistence-api")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-webflux")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.reactivestreams:reactive-streams")
    testImplementation("io.projectreactor:reactor-core")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.userContext.infrastructure_layer.springBoot.UserApplication"
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
    dependsOn(tasks.test)
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

task<JavaExec>("runUserApplication") {
    group = "userContext"
    description = "Runs the User Context application"
    mainClass = "com.userContext.infrastructure_layer.springBoot.UserApplication"

    classpath(sourceSets.main.get().runtimeClasspath)
}