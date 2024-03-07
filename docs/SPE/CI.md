# Continuous Integration with GitHub Actions

GitHub Actions provides a powerful platform for automating Continuous Integration (CI) workflows, allowing developers to streamline the build and testing processes of their projects directly within their GitHub repositories.

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
```

This GitHub Actions workflow automates the Gradle project's build process triggered by pushes to the main and develop branches. The workflow sets up the environment with JDK 19, configures Gradle, and executes the build tasks.
It ensures a consistent and efficient CI pipeline for Gradle-based development.

## Automatic Deployment to AWS.

```yaml
name: Deploy Application to AWS EC2 Instance
on:
  push:
    branches:
      - testDeploy

jobs:
  Deploy:
    name: Deploy to EC2
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v2
      - name: Build & Deploy
        env:
          PRIVATE_KEY: ${{ secrets.SSH_PRIVATE_KEY }}
          HOSTNAME: ${{secrets.SSH_HOST}}
          USER_NAME: ${{secrets.USER_NAME}}

        run: |
          echo "$PRIVATE_KEY" > ./private_key && chmod 600 ./private_key
          cat ./private_key
          ssh -o StrictHostKeyChecking=no -i ./private_key ${USER_NAME}@${HOSTNAME} '
          
          
              # Now we have got the access of EC2 and we will start the deploy .
              kubectl delete --all svc
              kubectl delete --all deploy
              kubectl delete --all pods
              sudo docker container delete vue-app
          
              minikube start
              cd my-dashboard/
          
              sudo docker pull leomarzoli/traffic_management_system:latest
              kubectl apply -f intersection-agents-deployment.yaml,intersection-agents-service.yaml,spring-db-app-deployment.yaml,spring-db-app-service.yaml,user-db-deployment.yaml,user-db-service.yaml
              docker run -it --rm --add-host=host.docker.internal:host-gateway -d -p 8080:80 --name vue-app leomarzoli/traffic_management_system:latest
          
              minikube tunnel -c
              '
```
This YAML file configures a GitHub Actions workflow for deploying an app to an AWS EC2 instance. Triggered by a push to either the'prepDeploy' or 'main' branch, establishes an SSH connection using secrets which are added to the secret repository section (in our case since the EC2 instance costs money, the secret regarding the host name, needs to be changed everytime the instance is turned off then on), and initiates deployment operations on the EC2 instance. It removes Kubernetes services and deployments and also deletes the containerized frontend via Docker of the frontend just in case any of these services were already started from a previous deployment, then it starts Minikube, applies Kubernetes services and deployments, and starts the frontend Docker container automatically by pulling it and running it, exposing port 8080, and also routing said port to port 80. The goal is to automate the application deployment process on a remote machine, streamlining the development cycle, and improving release management.

[Go Back.](./index.md) [Go Next.](./containerization.md)
