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
Questo file YAML configura un workflow GitHub Actions per il rilascio di un'app su un'istanza AWS EC2. Attivato dal push su 'testDeploy', clona il repository, stabilisce la connessione SSH e avvia operazioni di deploy sull'EC2. Elimina servizi e deployment Kubernetes ed elimina il frontend containerizzato tramite Docker se già presente, avvia Minikube, applica i servizi e i deployment di Kubernetes, e avvia il docker container Docker del frontend facendo automaticamente il pull. L'obiettivo è automatizzare il processo di distribuzione dell'applicazione sulla macchina remota, facilitando il ciclo di sviluppo e migliorando la gestione del rilascio.

[Go Back.](./index.md) [Go Next.](./containerization.md)
