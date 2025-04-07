# Backend of message routing application

This repository contains Rest endpoints and JMS listener for routing INBOUND and OUTBOUND messages of different partners

## Table of Contents

- Getting Started
- Prerequisites
- Installation
- Project Structure
- Rest endpoints
- Features
  - Message management
  - Partner Message management
- Running the Project

## Getting Started

To get a local copy of the project up and running, follow these steps.

### Prerequisites

- Java 21.0.6 (later)
- Maven 3.8.7 (or later)

### Installation

1. Clone the repository:

```sh
git clone https://github.com/dahdouh/backend.git
```

2. Navigate to the project directory:

```sh
cd backend
```

3.Install dependencies and build project:

```sh
mvn clean install
```

## Project Structure

Here's a brief overview of the project structure:

```sh
src/
├── main
│   ├── java
│   │   └── com
│   │       └── message
│   │           └── routing
│   │               ├── application
│   │               ├── domain
│   │               ├── input
│   │               └── output
│   └── resources
│       └── data
└── test
    └── java
        └── com
            └── message
                └── routing
                    ├── application
                    ├── input
                    └── output

```

### 1.2. Rest endpoints

| Method | Path          | Description                                 | Response |
| ------ | ------------- | ------------------------------------------- |-----------
| GET    | /message      | retrieve all the messages                   |200 OK
| POST   | /message      | publish a new message on MQ                 |201 CREATED
| GET    | /partner      | get all partners                            |200 OK
| POST   | /partner      | create an partner                           |201 CREATED
| DELETE | /partner/{id} | remove an partner by its ID                 |204 NO_CONTENT
| POST   | /auth/login   | sign in a user based on username & password |200 OK

## Features

### Message management

The JMS Listener consume messages on queue `DEV.QUEUE.1` and save them on `MESSAGE` table of `h2 database`

### Partner management

Ths application allows users to create, get and delete a partner.

For more information see the postman collection https://github.com/dahdouh/message-routing/blob/master/message-routing.postman_collection.json

## 1.3 Running the Project

To run the project locally, use the following command:

```sh
mvn spring-boot:run
```

Use the URL http://localhost:8080/ in your postman or curl request.
