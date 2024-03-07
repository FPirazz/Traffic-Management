#!/bin/bash

# Stop containers
docker stop vue-app
docker stop user_db
docker stop spring-db-app

# Remove containers
docker rm vue-app
docker rm user_db
docker rm spring-db-app

# Remove images
docker rmi tcm_frontend:latest fpirazzoli/sap_spe_db:latest src/main/userContext:latest