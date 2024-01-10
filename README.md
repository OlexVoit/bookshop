# README.md

## Introduction
Welcome to the **Bookshop** project! This web application serves as an online bookshop, providing functionalities for users to explore and manage books, categories, shopping carts, and orders. Below, you'll find key information about the project, its inspiration, technologies used, and how to set it up.

## Project Inspiration
The **Bookshop** project was inspired by the need for a user-friendly and efficient online bookshop platform. The goal is to provide users with a seamless experience in exploring, purchasing, and managing their favorite books.

## Technologies Used
The project leverages various technologies and tools, including but not limited to:
- **Java** - The primary programming language.
- **Spring Boot** - A powerful and flexible framework for building Java-based enterprise applications.
- **Spring Security** - Ensures secure authentication and authorization.
- **Spring Data JPA** - Simplifies database access using the Java Persistence API.
- **Swagger** - Provides an interactive API documentation.
- **MySQL** - A robust relational database management system.
- **Hibernate** - An object-relational mapping framework for Java.
- **Docker** - Simplifies the deployment process through containerization.
- **JUnit** - A widely used testing framework for Java applications.
- **MockMvc** - A testing framework that simplifies the testing of Spring MVC applications.

## Controllers Overview
### AuthenticationController
Handles user registration and login.
- `/api/auth/registration` - Register a new user.
- `/api/auth/login` - Log into an account.

### BookController
Manages operations related to books.
- `/api/books` - Get a list of books.
- `/api/books/{id}` - Find a book by ID.
- `/api/books` - Save a new book.
- `/api/books/{id}` - Update a book by ID.
- `/api/books/{id}` - Delete a book by ID.

### CategoryController
Deals with operations related to categories.
- `/api/categories` - Create a new category.
- `/api/categories` - Get a list of categories.
- `/api/categories/{id}` - Get a category by ID.
- `/api/categories/{id}` - Update a category by ID.
- `/api/categories/{id}` - Delete a category by ID.
- `/api/categories/{id}/books` - Get books by category ID.

### OrderController
Manages orders and order items.
- `/orders/{id}/items` - Get all items by order ID.
- `/orders` - Get all orders for a user.
- `/orders` - Place an order.
- `/orders/{id}` - Change order status.
- `/orders/{id}/items/{itemId}` - Get item from order by ID.

### ShoppingCartController
Handles shopping cart operations.
- `/api/cart` - Add books to the cart.
- `/api/cart` - Get the shopping cart.
- `/api/cart/{id}` - Delete a cart item.
- `/api/cart/cart-items/{id}` - Update the quantity of a book in the shopping cart.

## Setup Instructions
1. **Clone the repository.**
2. **Configure the database settings** in `application.properties`.
3. **Ensure tables are created** using Liquibase.
4. **Build and run the project** using your preferred IDE or `mvn spring-boot:run`.
5. **Access the API documentation** using Swagger at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Testing
The project is thoroughly tested to ensure robustness and reliability. Notable testing technologies include:
- **JUnit** - A widely used testing framework for Java applications.
- **MockMvc** - A testing framework that simplifies the testing of Spring MVC applications.

## Liquibase
Tables in the database are created using Liquibase, ensuring a consistent and version-controlled database structure.

## Challenges Faced
Throughout the development of the **Bookshop** project, I encountered various challenges, including:

### Challenge 1: Integration of Swagger
**Problem**: Integrating Swagger for API documentation posed initial difficulties as the configuration needed to align with the project structure.

**Solution**: After thorough research and consultation with the community, I successfully configured Swagger, enhancing the API documentation experience.

### Challenge 2: Handling Concurrent Orders
**Problem**: Ensuring a seamless experience for users placing orders simultaneously posed challenges in managing concurrent transactions.

**Solution**: Leveraged Spring's transaction management and optimistic locking to handle concurrent orders effectively, ensuring data consistency.

### Challenge 3: Docker Deployment
**Problem**: Deploying the application with Docker required overcoming compatibility issues and ensuring a smooth deployment process.

**Solution**: Explored Docker best practices, adjusted configurations, and successfully deployed the application in a Dockerized environment.

## Postman Requests
For your convenience, a collection of Postman requests is available [here](link_to_postman_collection). You can utilize these requests to interact with the API.

## Conclusion
Thank you for exploring our **Bookshop** project! We hope you find it engaging and user-friendly. For any inquiries or feedback, feel free to reach out. Happy reading!
