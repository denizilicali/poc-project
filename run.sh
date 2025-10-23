#!/bin/bash

echo "Starting Exxeta Project with Docker Compose..."

# Navigate to Docker directory
cd Docker

# Stop any existing containers
echo "Stopping existing containers..."
docker-compose down --remove-orphans

echo "  "
# Build and start all services
echo "Building and starting all services..."
docker-compose up --build -d

echo "  "
echo "All Services are started!"
