### README: Portfolio API

---

## Overview

The **Portfolio API** is a RESTful API designed to manage user portfolios, perform stock trading operations, and handle stock data. This API provides essential endpoints to create users, manage portfolios, upload stock data, and facilitate stock trading operations.

---

## API Specifications

### General Information

- **Title:** Portfolio API   
- **Base URL:** `http://localhost:8080`  

---

## Endpoints

### 1. **User API**

#### a) Create User
- **Endpoint:** `/user/create`  
- **Method:** POST  
- **Description:** Creates a new user.  
- **Request Body:**  
  ```json
  {
    "name": "string",
    "email": "string"
  }
  ```
- **Response:**  
  - **Status Code:** 200 OK  
  - **Body:** String confirmation message  

---

#### b) Get All Users
- **Endpoint:** `/user/get`  
- **Method:** GET  
- **Description:** Retrieves a list of all users.  
- **Response:**  
  - **Status Code:** 200 OK  
  - **Body:** Array of user objects:
    ```json
    [
      {
        "id": 1,
        "name": "string",
        "email": "string",
        "holdingList": [
          {
            "price": 0.0,
            "quantity": 0.0,
            "stock": {
              "id": 1,
              "name": "string",
              "openPrice": 0.0,
              "closePrice": 0.0,
              "highPrice": 0.0,
              "lowPrice": 0.0,
              "settlementPrice": 0.0
            }
          }
        ]
      }
    ]
    ```

---

#### c) Get User by ID
- **Endpoint:** `/user/get/{id}`  
- **Method:** GET  
- **Description:** Retrieves details of a specific user by ID.  
- **Path Parameters:**  
  - `id` (integer): User ID  
- **Response:**  
  - **Status Code:** 200 OK  
  - **Body:**  
    ```json
    {
      "name": "string",
      "email": "string",
      "totalExpenditure": 0.0,
      "profit": 0.0,
      "holdingDTOList": [
        {
          "stockId": 1,
          "netQuantity": 0.0,
          "netPrice": 0.0,
          "currentPrice": 0.0,
          "profit": 0.0
        }
      ]
    }
    ```

---

### 2. **Trade API**

#### Exchange Stock
- **Endpoint:** `/trading/exchange`  
- **Method:** POST  
- **Description:** Executes a stock trade operation (buy/sell).  
- **Request Body:**  
  ```json
  {
    "userId": 1,
    "type": "BUY",
    "quantity": 10.0,
    "stockId": 1
  }
  ```
  - `type` can be either `"BUY"` or `"SELL"`.  
- **Response:**  
  - **Status Code:** 200 OK  
  - **Body:** String confirmation message  

---

### 3. **Stocks API**

#### a) Upload Stock Data
- **Endpoint:** `/stock/upload`  
- **Method:** POST  
- **Description:** Uploads stock data as a binary file.  
- **Request Body:**  
  ```json
  {
    "file": "string (binary)"
  }
  ```
- **Response:**  
  - **Status Code:** 200 OK  
  - **Body:** String confirmation message  

---

#### b) Get Stock by ID
- **Endpoint:** `/stock/get/{id}`  
- **Method:** GET  
- **Description:** Retrieves stock details by ID.  
- **Path Parameters:**  
  - `id` (integer): Stock ID  
- **Response:**  
  - **Status Code:** 200 OK  
  - **Body:**  
    ```json
    {
      "id": 1,
      "name": "string",
      "openPrice": 0.0,
      "closePrice": 0.0,
      "highPrice": 0.0,
      "lowPrice": 0.0,
      "settlementPrice": 0.0
    }
    ```

---

### 4. **Testing API**

#### Get API Status
- **Endpoint:** `/`  
- **Method:** GET  
- **Description:** Checks the API status.  
- **Response:**  
  - **Status Code:** 200 OK  
  - **Body:** String confirmation message  

---

## Components

### Schemas

#### 1. **UserDTO**
```json
{
  "name": "string",
  "email": "string"
}
```

#### 2. **ExchangeDTO**
```json
{
  "userId": 1,
  "type": "BUY",
  "quantity": 0.0,
  "stockId": 1
}
```

#### 3. **StockEntity**
```json
{
  "id": 1,
  "name": "string",
  "openPrice": 0.0,
  "closePrice": 0.0,
  "highPrice": 0.0,
  "lowPrice": 0.0,
  "settlementPrice": 0.0
}
```

#### 4. **StockHolding**
```json
{
  "price": 0.0,
  "quantity": 0.0,
  "stock": { /* StockEntity */ }
}
```

#### 5. **UserEntity**
```json
{
  "id": 1,
  "name": "string",
  "email": "string",
  "holdingList": [ /* Array of StockHolding */ ]
}
```

#### 6. **StockHoldingDTO**
```json
{
  "stockId": 1,
  "netQuantity": 0.0,
  "netPrice": 0.0,
  "currentPrice": 0.0,
  "profit": 0.0
}
```

#### 7. **UserPortfolioDTO**
```json
{
  "name": "string",
  "email": "string",
  "totalExpenditure": 0.0,
  "profit": 0.0,
  "holdingDTOList": [ /* Array of StockHoldingDTO */ ]
}
```

---

## Usage Instructions

1. Clone the repository and run the server locally.
2. Use the defined endpoints to interact with the API.
3. Ensure request bodies follow the respective schema for successful operations.

---
