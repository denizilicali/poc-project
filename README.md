# Exxeta Project

This project contains three Spring Boot microservices that can be run using Docker Compose.

## Services

1. **bestandsysteme** - Runs on port 8081, uses MySQL database
2. **middleware-textvorsystem** - Runs on port 8082, uses PostgreSQL database and ActiveMQ
3. **tesys** - Runs on port 8083, uses MySQL database and ActiveMQ

## Prerequisites

- Docker
- Docker Compose

## Running the Project

### Option 1: Using the run script (Recommended)
```bash
./run.sh
```

### Option 2: Manual Docker Compose commands

1. Navigate to the Docker directory:
   ```bash
   cd Docker
   ```

2. Start all services:
   ```bash
   docker-compose up --build
   ```

3. To run in detached mode:
   ```bash
   docker-compose up --build -d
   ```

### Option 3: Start services individually
```bash
# Start infrastructure services first
docker-compose up mysql postgres activemq

# Then start the application services
docker-compose up bestandsysteme middleware-textvorsystem tesys
```

## Service Endpoints

- **bestandsysteme**: http://localhost:8081
- **middleware-textvorsystem**: http://localhost:8082
- **tesys**: http://localhost:8083
- **ActiveMQ Management Console**: http://localhost:8161 (admin/admin)

## Database Access

- **MySQL**: localhost:3306 (myuser/secret)
- **PostgreSQL**: localhost:5433 (myuser/secret) - Note: Using port 5433 to avoid conflicts

## Stopping the Services

```bash
docker-compose down
```

To also remove volumes:
```bash
docker-compose down -v
```

## Project Structure

```
exxeta-project/
├── Docker/
│   ├── compose.yaml
│   └── initdb/
│       └── 01_schema.sql
├── src/
│   ├── bestandsysteme/
│   │   ├── Dockerfile
│   │   └── src/main/java/com/exxeta/demo/bestandsysteme/
│   ├── middleware-textvorsystem/
│   │   ├── Dockerfile
│   │   └── src/main/java/com/exxeta/demo/middlewaretextvorsystem/
│   └── tesys/
│       ├── Dockerfile
│       └── src/main/java/com/exxeta/demo/tesys/
└── README.md
```
