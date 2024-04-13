# Customer Account API

API for opening a new "current account" for existing customers.


## Introduction

This API enables existing customers to open new "current accounts".

#### Requirements

• The API will expose an endpoint which accepts the user information (customerID,
initialCredit).

• Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.

• Also, if initialCredit is not 0, a transaction will be sent to the new account.

• Another Endpoint will output the user information showing Name, Surname, balance, and
transactions of the accounts.

-------


## Tech Stack

- JDK 8
- Maven
- Spring Boot
- H2 DB
- JUnit/Mockito
- HTML for Frontend


## CI/CD Tools
- GitHub Actions
- Maven
- Docker
- Docker Hub


## Run & Build 

1. **Clone the repository:**

    git clone https://github.com/shikhabhaisare/customer-account.git
    

2. **Navigate to the root project:**
	
	cd customer-account


    **Execute below maven command to build the project:** 
    
    	 mvn clean install


    **Execute below maven command to run the application:** 
    
    	 mvn spring-boot:run

   

## Initial Database setup

 **SQL Script:**

INSERT INTO customers(name, surname) VALUES
  ('Neha', 'Roy'),
  ('Vishal', 'Arora'),
  ('Trupti', 'Naik');


**-Result**



| id | name   |  surname |	
| -- | ------ | ---------|
| 1  | Neha   |  Roy	 |
| 2  | Vishal |  Arora   |
| 3  | Trupti |  Naik    |


## API EndPoints 

The application has 2 apis :

#### 1. AccountAPI  : Open a new current account for existing customer ####

endpoint:


  	POST - http://localhost:8080/api/v1/accounts 
  

#### 2. CustomerAPI : Retrieves a customer information ####

endpoint:


  	GET - http://localhost:8080/api/v1/customers/{customerId}
  

  $ *path parameter:*
  customerId = Id of the customer
  

## Frontend Endpoints

**Show all customers:** 

http://localhost:8080/web/v1/customers/

**Show customer accounts:** 

http://localhost:8080/web/v1/customers/1

**Show account transactions:** 

http://localhost:8080/web/v1/customers/1/accounts/1



## Swagger UI will be run on this url:


http://localhost:8080/swagger-ui.html#/


