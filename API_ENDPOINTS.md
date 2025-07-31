# Jewelverse API Documentation

## Overview
Jewelverse is a jewelry store management system built with Spring Boot 3.4.5. This API provides endpoints for authentication, user management, jewelry management, and discount request handling.

**Base URL:** `http://localhost:8080`  
**Database:** H2 In-Memory Database  
**H2 Console:** `http://localhost:8080/h2-console`

---

## Authentication

### Public Authentication Endpoints

#### 1. Register Customer
- **URL:** `POST /api/auth/register`
- **Description:** Register a new customer account
- **Authentication:** None required

**Request Body:**
```json
{
  "firstName": "string",
  "lastName": "string", 
  "email": "string",
  "password": "string"
}
```

**Response:**
- **Success (201):** `"User registered successfully!"`
- **Error (400):** User already exists or validation error

---

#### 2. Login
- **URL:** `POST /api/auth/login`
- **Description:** Authenticate user and receive JWT token
- **Authentication:** None required

**Request Body:**
```json
{
  "email": "string",
  "password": "string"
}
```

**Response:**
```json
{
  "token": "string",
  "role": "CUSTOMER|ADMIN|MANAGER|SALES_ASSISTANT",
  "firstName": "string",
  "lastName": "string",
  "email": "string"
}
```

---

## Customer Endpoints

### Basic Customer Endpoints

#### 3. Customer Test Message 1
- **URL:** `GET /api/customer/message1`
- **Description:** Test endpoint for customer authentication
- **Authentication:** Required (Customer role)

**Response:**
```json
"Hello from UserController"
```

#### 4. Customer Test Message 2
- **URL:** `GET /api/customer/message2`
- **Description:** Test endpoint for customer authentication
- **Authentication:** Required (Customer role)

**Response:**
```json
"Hello from UserController2"
```

---

## Admin Endpoints

### User Management (Admin Only)

#### 5. Create User
- **URL:** `POST /api/admin/users`
- **Description:** Create a new user (Admin, Manager, or Sales Assistant)
- **Authentication:** Required (Admin role)

**Request Body:**
```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "role": "ADMIN|MANAGER|SALES_ASSISTANT"
}
```

**Response:**
```json
{
  "id": "number",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "role": "ADMIN|MANAGER|SALES_ASSISTANT",
  "enabled": "boolean",
  "createdAt": "2025-07-31T22:57:07.250"
}
```

---

#### 6. Get All Users
- **URL:** `GET /api/admin/users`
- **Description:** Retrieve all users (excluding customers)
- **Authentication:** Required (Admin role)

**Response:**
```json
[
  {
    "id": "number",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "role": "ADMIN|MANAGER|SALES_ASSISTANT",
    "enabled": "boolean",
    "createdAt": "2025-07-31T22:57:07.250"
  }
]
```

---

#### 7. Get User by ID
- **URL:** `GET /api/admin/users/{id}`
- **Description:** Retrieve specific user by ID
- **Authentication:** Required (Admin role)

**Path Parameters:**
- `id` (number): User ID

**Response:**
```json
{
  "id": "number",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "role": "ADMIN|MANAGER|SALES_ASSISTANT",
  "enabled": "boolean",
  "createdAt": "2025-07-31T22:57:07.250"
}
```

---

#### 8. Update User
- **URL:** `PUT /api/admin/users/{id}`
- **Description:** Update user information
- **Authentication:** Required (Admin role)

**Path Parameters:**
- `id` (number): User ID

**Request Body:**
```json
{
  "firstName": "string",
  "lastName": "string",
  "role": "ADMIN|MANAGER|SALES_ASSISTANT",
  "enabled": "boolean"
}
```

**Response:**
```json
{
  "id": "number",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "role": "ADMIN|MANAGER|SALES_ASSISTANT",
  "enabled": "boolean",
  "createdAt": "2025-07-31T22:57:07.250"
}
```

---

#### 9. Delete User
- **URL:** `DELETE /api/admin/users/{id}`
- **Description:** Delete a user account
- **Authentication:** Required (Admin role)

**Path Parameters:**
- `id` (number): User ID

**Response:**
- **Success (204):** No content

---

## Public Jewelry Endpoints

#### 10. Get All Jewelry
- **URL:** `GET /api/jewelry`
- **Description:** Retrieve all jewelry items for public display
- **Authentication:** None required

**Response:**
```json
[
  {
    "id": "number",
    "name": "string",
    "description": "string",
    "price": "number",
    "material": "string",
    "weight": "number",
    "dimensions": "string",
    "sku": "string",
    "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
    "categoryName": "string",
    "stockQuantity": "number",
    "imageUrls": ["string"],
    "createdAt": "2025-07-31T22:57:07.250",
    "updatedAt": "2025-07-31T22:57:07.250"
  }
]
```

---

#### 11. Get Jewelry by ID
- **URL:** `GET /api/jewelry/{id}`
- **Description:** Retrieve specific jewelry item by ID
- **Authentication:** None required

**Path Parameters:**
- `id` (number): Jewelry ID

**Response:**
```json
{
  "id": "number",
  "name": "string",
  "description": "string",
  "price": "number",
  "material": "string",
  "weight": "number",
  "dimensions": "string",
  "sku": "string",
  "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
  "categoryName": "string",
  "stockQuantity": "number",
  "imageUrls": ["string"],
  "createdAt": "2025-07-31T22:57:07.250",
  "updatedAt": "2025-07-31T22:57:07.250"
}
```

---

## Manager Endpoints

### Jewelry Management (Manager Only)

#### 12. Create Jewelry Item
- **URL:** `POST /api/manager/jewelry`
- **Description:** Create a new jewelry item
- **Authentication:** Required (Manager role)

**Request Body:**
```json
{
  "name": "string",
  "description": "string",
  "price": "number",
  "material": "string",
  "weight": "number",
  "dimensions": "string",
  "sku": "string",
  "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
  "categoryId": "number",
  "initialStock": "number",
  "imageUrls": ["string"]
}
```

**Response:**
```json
{
  "id": "number",
  "name": "string",
  "description": "string",
  "price": "number",
  "material": "string",
  "weight": "number",
  "dimensions": "string",
  "sku": "string",
  "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
  "categoryName": "string",
  "stockQuantity": "number",
  "imageUrls": ["string"],
  "createdAt": "2025-07-31T22:57:07.250",
  "updatedAt": "2025-07-31T22:57:07.250"
}
```

---

#### 13. Update Jewelry Item
- **URL:** `PUT /api/manager/jewelry/{id}`
- **Description:** Update existing jewelry item
- **Authentication:** Required (Manager role)

**Path Parameters:**
- `id` (number): Jewelry ID

**Request Body:**
```json
{
  "name": "string",
  "description": "string",
  "price": "number",
  "material": "string",
  "weight": "number",
  "dimensions": "string",
  "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
  "categoryId": "number"
}
```

**Response:**
```json
{
  "id": "number",
  "name": "string",
  "description": "string",
  "price": "number",
  "material": "string",
  "weight": "number",
  "dimensions": "string",
  "sku": "string",
  "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
  "categoryName": "string",
  "stockQuantity": "number",
  "imageUrls": ["string"],
  "createdAt": "2025-07-31T22:57:07.250",
  "updatedAt": "2025-07-31T22:57:07.250"
}
```

---

#### 14. Delete Jewelry Item
- **URL:** `DELETE /api/manager/jewelry/{id}`
- **Description:** Delete a jewelry item
- **Authentication:** Required (Manager role)

**Path Parameters:**
- `id` (number): Jewelry ID

**Response:**
- **Success (204):** No content

---

#### 15. Get All Jewelry (Manager View)
- **URL:** `GET /api/manager/jewelry`
- **Description:** Retrieve all jewelry items (manager view with full details)
- **Authentication:** Required (Manager role)

**Response:**
```json
[
  {
    "id": "number",
    "name": "string",
    "description": "string",
    "price": "number",
    "material": "string",
    "weight": "number",
    "dimensions": "string",
    "sku": "string",
    "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
    "categoryName": "string",
    "stockQuantity": "number",
    "imageUrls": ["string"],
    "createdAt": "2025-07-31T22:57:07.250",
    "updatedAt": "2025-07-31T22:57:07.250"
  }
]
```

---

#### 16. Get Jewelry by ID (Manager View)
- **URL:** `GET /api/manager/jewelry/{id}`
- **Description:** Retrieve specific jewelry item (manager view)
- **Authentication:** Required (Manager role)

**Path Parameters:**
- `id` (number): Jewelry ID

**Response:**
```json
{
  "id": "number",
  "name": "string",
  "description": "string",
  "price": "number",
  "material": "string",
  "weight": "number",
  "dimensions": "string",
  "sku": "string",
  "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
  "categoryName": "string",
  "stockQuantity": "number",
  "imageUrls": ["string"],
  "createdAt": "2025-07-31T22:57:07.250",
  "updatedAt": "2025-07-31T22:57:07.250"
}
```

---

## Sales Assistant Endpoints

#### 17. View All Jewelry (Sales View)
- **URL:** `GET /api/sales/jewelry`
- **Description:** View all jewelry items with stock information
- **Authentication:** Required (Sales Assistant role)

**Response:**
```json
[
  {
    "id": "number",
    "name": "string",
    "description": "string",
    "price": "number",
    "material": "string",
    "weight": "number",
    "dimensions": "string",
    "sku": "string",
    "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
    "categoryName": "string",
    "stockQuantity": "number",
    "imageUrls": ["string"],
    "createdAt": "2025-07-31T22:57:07.250",
    "updatedAt": "2025-07-31T22:57:07.250"
  }
]
```

---

#### 18. View Jewelry by ID (Sales View)
- **URL:** `GET /api/sales/jewelry/{id}`
- **Description:** View specific jewelry item details
- **Authentication:** Required (Sales Assistant role)

**Path Parameters:**
- `id` (number): Jewelry ID

**Response:**
```json
{
  "id": "number",
  "name": "string",
  "description": "string",
  "price": "number",
  "material": "string",
  "weight": "number",
  "dimensions": "string",
  "sku": "string",
  "status": "ACTIVE|INACTIVE|OUT_OF_STOCK|DISCONTINUED",
  "categoryName": "string",
  "stockQuantity": "number",
  "imageUrls": ["string"],
  "createdAt": "2025-07-31T22:57:07.250",
  "updatedAt": "2025-07-31T22:57:07.250"
}
```

---

#### 19. Request Special Discount
- **URL:** `POST /api/sales/discounts/request`
- **Description:** Submit a special discount request for customer
- **Authentication:** Required (Sales Assistant role)

**Request Body:**
```json
{
  "jewelryId": "number",
  "salesAssistantId": "number",
  "requestedDiscountPercentage": "number",
  "reason": "string"
}
```

**Response:**
```json
{
  "id": "number",
  "jewelryName": "string",
  "requestedByUserName": "string",
  "requestedDiscountPercentage": "number",
  "reason": "string",
  "status": "PENDING|APPROVED|REJECTED",
  "reviewedByUserName": "string",
  "managerComment": "string",
  "createdAt": "2025-07-31T22:57:07.250"
}
```

---

## Data Models & Enums

### UserRole Enum
```
CUSTOMER
ADMIN
MANAGER
SALES_ASSISTANT
```

### JewelryStatus Enum
```
ACTIVE
INACTIVE
OUT_OF_STOCK
DISCONTINUED
```

### DiscountRequestStatus Enum
```
PENDING
APPROVED
REJECTED
```

---

## Error Responses

### Common Error Format
```json
{
  "timestamp": "2025-07-31T22:57:07.250",
  "status": "number",
  "error": "string",
  "message": "string",
  "path": "string"
}
```

### Common HTTP Status Codes
- **200 OK:** Request successful
- **201 Created:** Resource created successfully
- **204 No Content:** Request successful, no response body
- **400 Bad Request:** Invalid request data
- **401 Unauthorized:** Authentication required
- **403 Forbidden:** Insufficient permissions
- **404 Not Found:** Resource not found
- **500 Internal Server Error:** Server error

---

## Authentication

### JWT Token Usage
All protected endpoints require a JWT token in the Authorization header:

```
Authorization: Bearer <jwt_token>
```

The token is obtained from the login endpoint and contains user role information for authorization.

### Role-Based Access Control
- **Public endpoints:** No authentication required
- **Customer endpoints:** Require CUSTOMER role
- **Admin endpoints:** Require ADMIN role  
- **Manager endpoints:** Require MANAGER role
- **Sales Assistant endpoints:** Require SALES_ASSISTANT role

---

## Database Information

- **Type:** H2 In-Memory Database
- **URL:** `jdbc:h2:mem:testdb`
- **Console:** `http://localhost:8080/h2-console`
- **Username:** `SA`
- **Password:** (empty)

### Key Tables
- `users` - User accounts with single-table inheritance
- `roles` - User roles
- `jewelries` - Jewelry items
- `categories` - Jewelry categories
- `inventory` - Stock management
- `discount_requests` - Special discount requests
- `discounts` - Applied discounts
- `orders` / `order_items` - Order management
- `carts` / `cart_items` - Shopping cart
- `feedbacks` - Customer reviews
- `payments` - Payment tracking

---

## Notes

1. **Development Mode:** Currently using H2 in-memory database for testing
2. **Security:** JWT-based authentication with role-based authorization
3. **Validation:** Request validation using Spring Boot Validation
4. **Documentation:** This API follows RESTful conventions
5. **Incomplete Features:** Some controller classes (CustomerController, CustomerCartController, etc.) are placeholder classes and may require implementation based on business requirements

---

*Last Updated: July 31, 2025*
