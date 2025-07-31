# Database Setup Instructions

## MySQL Database Setup

### Prerequisites
1. Install MySQL Server 8.0+ on your system
2. Start MySQL service

### Database Creation
Connect to MySQL as root and run these commands:

```sql
-- Create database
CREATE DATABASE jewelverse_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Create a dedicated user (recommended for production)
CREATE USER 'jewelverse_user'@'localhost' IDENTIFIED BY 'jewelverse_password';
GRANT ALL PRIVILEGES ON jewelverse_db.* TO 'jewelverse_user'@'localhost';
FLUSH PRIVILEGES;

-- Show databases to verify creation
SHOW DATABASES;
```

### Application Configuration

The application is configured to connect to MySQL with these default settings:

- **Database URL:** `jdbc:mysql://localhost:3306/jewelverse_db`
- **Username:** `root`
- **Password:** `password`

**To customize the connection, update these properties in `application.properties`:**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jewelverse_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Alternative Database Options

#### Using Docker MySQL (Recommended for Development)

```bash
# Pull and run MySQL in Docker
docker run --name jewelverse-mysql \
  -e MYSQL_ROOT_PASSWORD=password \
  -e MYSQL_DATABASE=jewelverse_db \
  -e MYSQL_USER=jewelverse_user \
  -e MYSQL_PASSWORD=jewelverse_password \
  -p 3306:3306 \
  -d mysql:8.0

# Connect to the container to run SQL commands
docker exec -it jewelverse-mysql mysql -u root -p
```

#### Using PostgreSQL (Alternative)

If you prefer PostgreSQL, update the dependencies and configuration:

**pom.xml:**
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

**application.properties:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jewelverse_db
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### Database Schema

The application will automatically create the following tables when started:

- `users` - User accounts (Admin, Manager, Sales Assistant, Customer)
- `categories` - Jewelry categories
- `jewelries` - Jewelry items
- `inventory` - Stock management
- `carts` / `cart_items` - Shopping cart
- `orders` / `order_items` - Order management
- `discounts` - Discount configurations
- `discount_requests` - Special discount requests
- `feedbacks` - Customer reviews
- `payments` - Payment tracking
- `jewelry_images` - Product images

### Troubleshooting

#### Common Issues:

1. **Connection refused:**
   - Ensure MySQL service is running
   - Check port 3306 is accessible
   - Verify credentials

2. **Database doesn't exist:**
   - Create the database manually: `CREATE DATABASE jewelverse_db;`

3. **Authentication plugin error:**
   - Update user authentication: `ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';`

4. **Timezone issues:**
   - The URL includes `serverTimezone=UTC` to handle timezone properly

#### Verification Commands:

```sql
-- Check if database exists
SHOW DATABASES LIKE 'jewelverse%';

-- Check tables after application startup
USE jewelverse_db;
SHOW TABLES;

-- Verify user_role enum is working
DESCRIBE users;
```

### Production Considerations

1. **Change default passwords**
2. **Use environment variables for credentials**
3. **Enable SSL connections**
4. **Configure proper backup strategies**
5. **Set up monitoring and logging**

Example environment-based configuration:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/jewelverse_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:password}
```
