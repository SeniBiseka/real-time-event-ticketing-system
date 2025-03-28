
# Real-Time Event Ticketing System

This project is a Real-Time Event Ticketing System implemented using Spring Boot for the backend. It simulates a ticketing environment where multiple vendors release tickets, and multiple customers purchase them concurrently. The system is designed to handle concurrency safely using multi-threading and synchronization.


## Features

- Configuration via CLI
- Concurrent ticket addition and purchase
- REST API with Spring Boot
- Logging system activities and errors.


## Technologies Used

- Java
- Spring Boot
- REST API
- Multi-threading (Runnable, synchronized methods)
- Gson (for JSON serialization)
- Postman (for API testing)


## Setup Instructions
1. Clone the repository.
2. Navigate to the project directory.
3. Run mvn spring-boot:run.
4. Access the API:
   - Open Postman or browser and test endpoints.
   - Example: http://localhost:8080/api/tickets/available


## API Reference

#### start the program

```http://localhost:8080/api/tickets/start
  POST /api/tickets/start
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `customerQuantity` | `int` | how many customers trying to buy tickets. |

#### Get available ticket

```http://localhost:8080/api/tickets/available
  GET /api/tickets/available
```
#### Get configuration

```http://localhost:8080/api/tickets/config
  GET /api/tickets/config
```

## Testing Instructions
1. Run the application.
2. Use Postman or browser to send API requests.
3. Check logs (system_logs.txt) for ticketing actions.
