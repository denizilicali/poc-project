#!/bin/bash

echo "Starting Exxeta Project with Docker Compose..."

# Navigate to Docker directory
cd Docker

# Stop any existing containers
echo "Stopping existing containers..."
docker-compose down --remove-orphans

# Build and start all services
echo "Building and starting all services..."
docker-compose up --build

echo "All services started!"
echo ""
echo "Service endpoints:"
echo "- bestandsysteme: http://localhost:8081"
echo "- middleware-textvorsystem: http://localhost:8082"
echo "- tesys: http://localhost:8083"
echo "- ActiveMQ Management Console: http://localhost:8161 (admin/admin)"
echo ""
echo "Database connections:"
echo "- MySQL: localhost:3306 (myuser/secret)"
echo "- PostgreSQL: localhost:5432 (myuser/secret)"
echo ""
echo "To stop all services, run: docker-compose down"

