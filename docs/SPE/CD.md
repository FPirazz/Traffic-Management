# Continuous Deployment with AWS and Kubernetes

## Overview

Continuous Deployment (CD) is a critical aspect of modern software development, automating the release and deployment processes to deliver new features and improvements seamlessly. In this project, CD is facilitated through the integration of Amazon Web Services (AWS) and Kubernetes.

## Amazon Web Services (AWS)

Amazon Web Services (AWS) is a comprehensive cloud computing platform offered by Amazon. It provides a wide range of services, including computing power, storage, databases, machine learning, analytics, and more. AWS enables developers to build scalable and flexible applications without the need for extensive infrastructure management.

## Continuous Deployment Workflow

The CD workflow is orchestrated using Kubernetes, a container orchestration platform, along with AWS services. Here's an overview of the process:

1. **Minikube Setup**: Before starting the deployment, ensure Docker Desktop is open, and Minikube is installed. Execute `minikube start` to initiate the Minikube cluster.

2. **Kubernetes Deployment**: Navigate to the project directory and execute the following commands to deploy the Kubernetes services:

    ```bash
    kubectl apply -f intersection-agents-deployment.yaml,intersection-agents-service.yaml,spring-db-app-deployment.yaml,spring-db-app-service.yaml,user-db-deployment.yaml,user-db-service.yaml
    ```

3. **Minikube Tunneling**: Start Minikube tunneling to expose services locally:

    ```bash
    minikube tunnel
    ```

4. **Check Service IPs**: In a separate terminal, verify that the external IPs of the services are set to `127.0.0.1`:

    ```bash
    kubectl get svc
    ```

5. **Docker Compose for Vue**: Build and run the Vue application container:

    ```bash
    docker compose build --no-cache
    docker run -it --rm -d -p 8080:80 --name vue-app traffic-management-vue-app:latest
    ```

6. **Accessing the Application**: Visit `localhost:8080` in your browser to access the application. The Vue app communicates with Kubernetes clusters (Spring, User-DB, Intersection Agents) through AWS services.

## AWS Integration

AWS plays a crucial role in this CD workflow by hosting the Vue application and the Nginx reverse proxy. The Nginx proxy routes HTTP calls from the browser to the appropriate Kubernetes clusters. AWS services, such as EC2 for virtual servers and S3 for storage, provide a scalable and reliable infrastructure for hosting and serving the application.

This CD approach ensures that changes are seamlessly deployed to the Kubernetes clusters, providing a smooth and efficient development and deployment pipeline.


[Go Back.](./index.md)
