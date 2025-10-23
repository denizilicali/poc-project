Running the Project

Option 1: Using the run script
./run.sh

Option 2: Manual Docker Compose commands
1)Navigate to the Docker directory:
     cd Docker
2) Start all services: 
     docker-compose up --build

To run in detached mode: docker-compose up --build -d


Service Endpoints
* bestandsysteme: http://localhost:8081
* middleware-textvorsystem: http://localhost:8082
* tesys: http://localhost:8084
* ActiveMQ Management Console: http://localhost:8161 (admin/admin)



