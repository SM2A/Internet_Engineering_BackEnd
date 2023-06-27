#!/bin/bash

echo "=============================================================================="
echo "========================== Baloot Deployment Script =========================="
echo "=============================================================================="

echo "\n********************* Removing target directory *********************\n"
mvn clean

echo "\n************************* Building project **************************\n"
mvn install -DskipTests

echo "\n********************* Stopping previous containers ******************\n"
docker-compose -f ./docker/docker-compose.yml down

#echo "\n********************* Removing previous images ******************\n"
#docker system prune -af

echo "\n********************* Removing previous volumes ******************\n"
docker volume prune -f

echo "\n*********************** Starting build.... *************************\n"
mvn docker:removeImage package docker:build -DskipTests

echo "\n*********************** Launching Containers.... *************************\n"
docker-compose -f ./docker/docker-compose.yml up
