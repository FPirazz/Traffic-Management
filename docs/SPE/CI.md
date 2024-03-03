# Continuous Integration with GitHub Actions

## Build Gradle Project Workflow

```yaml
name: Build Gradle project

on:
  push:
    branches:
      - main
      - develop
      
jobs:
  build-gradle-project:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout project sources
        uses: actions/checkout@v4
      - name: Set up JDK 19
        uses: actions/setup-java@v3
        with:
          java-version: '19'
          distribution: 'temurin' # Or other distributions like 'adopt'
      - name: Setup Gradle
        uses: gradle/actions/setup-gradle@v3
      - name: Execute Gradle build
        run: ./gradlew build

This GitHub Actions workflow automates the Gradle project's build process triggered by pushes to the main and develop branches. The workflow sets up the environment with JDK 19, configures Gradle, and executes the build tasks.
It ensures a consistent and efficient CI pipeline for Gradle-based development.
