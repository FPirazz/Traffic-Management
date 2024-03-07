# Continuous Deployment with AWS and Kubernetes

## Overview

Continuous Deployment (CD) is a critical aspect of modern software development, automating the release and deployment processes to deliver new features and improvements seamlessly. In this project, CD is facilitated through the integration of Amazon Web Services (AWS) and Kubernetes.

## Amazon Web Services (AWS)

Amazon Web Services (AWS) is a comprehensive cloud computing platform offered by Amazon. It provides a wide range of services, including computing power, storage, databases, machine learning, analytics, and more. AWS enables developers to build scalable and flexible applications without the need for extensive infrastructure management.

## Continuous Deployment Workflow

The CD workflow is orchestrated using Kubernetes, a container orchestration platform, Docker, since the frontend will be hosted on a container, and also Kubernetes is supported my the Minikube technology which requires the instantiation of a minikube container, along with AWS services. Here's an overview of the process:

1. **Minikube Setup**: Before starting the deployment, ensure Docker Desktop, or the Docker daemon, is running, and Minikube is installed. Execute `minikube start` to initiate the Minikube cluster.

2. **Kubernetes Deployment**: Navigate to the directory containing the files regaring CD and execute the following commands to deploy the Kubernetes services:

    ```bash
    kubectl apply -f intersection-agents-deployment.yaml,intersection-agents-service.yaml,spring-db-app-deployment.yaml,spring-db-app-service.yaml,user-db-deployment.yaml,user-db-service.yaml
    ```

3. **Docker Compose for Vue**: Build and run the Vue application container:

    ```bash
    docker compose build --no-cache
    docker run -it --rm -d -p 8080:80 --name vue-app traffic-management-vue-app:latest
    ```

4. **Minikube Tunneling**: Start Minikube tunneling to expose services locally:

    ```bash
    minikube tunnel
    ```

5. **Check Service IPs**: In a separate terminal, verify that the external IPs of the services are set to their assigned Cluster-IPs:

    ```bash
    kubectl get svc
    ```
    
6. **Accessing the Application**: Visit the hostname where the application is being hosted, on the port 8008 in your browser, to access the application. The Vue app communicates with Kubernetes clusters (Spring, User-DB, Intersection Agents) through AWS services.

## AWS Integration

AWS plays a crucial role in this CD workflow by hosting the Vue application, a Nginx reverse proxy and the Kubernets services.

The Vue containerized Vue application itself also contains an internal Nginx reverse-proxy, used to redirect any Axios requests (RPC requests) made towards the services properly, in our case the requests are re-directed to another Nginx proxy instantiated in the EC2 instance itself.
The second Nginx proxy routes HTTP calls from the browser to the appropriate Kubernetes clusters, mainly by letting the frontend know where each REST endpoint is.
And finally each Kubernetes service exposes an external IP, on a port, to let request come in and be processed.

This CD approach ensures that changes are seamlessly deployed to the Kubernetes clusters, providing a smooth and efficient development and deployment pipeline, which is also made secure by the fact that the only port that is accessibile externally from the EC2 instance it's only the one related to the frontend, so no other request can be done on other ports. In our case, because of the fact that this is an academic project, the EC2 instance is only turned on when necessary because running any type of service on AWS costs money, so therefore we only test deployment when necessary, but in a real world scenario, the instance/s can be left running, also employing other EC2 services such as requesting a static IP for the instance.


[Go Back.](./index.md)
