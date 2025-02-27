
# Real-Time Event Ticketing System

A multi-threaded ticketing system using Spring Boot that simulates real-time ticket addition and purchase with vendor and customer interactions.


## Features

- Configuration via CLI
- Concurrent ticket addition and purchase
- REST API with Spring Boot


## Setup Instructions
1. Clone the repository.
2. Navigate to the project directory.
3. Run mvn spring-boot:run. 
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
