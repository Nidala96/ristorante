# Mini Restaurant
This project implements a set of REST APIs for simplified restaurant management, using Java Spring Boot and Spring Security with JWT and OAuth2 for authentication and authorization.

## Technical Specifications:

Restaurant Registration:
- Endpoint: POST /api/restaurant
- Parameters: id, email, password, restaurant name
### OAuth2 Login:
- Endpoint: POST /api/login
- Parameters: email, password
### Authenticated User-only APIs:
- Create a new ingredient: POST /api/ingredients
- Create a new dish: POST /api/dishes
- Get the list of ingredients: GET /api/ingredients
- Get the list of dishes: GET /api/dishes
- Delete an ingredient: DELETE /api/ingredients/{id}
- Delete a dish: DELETE /api/dishes/{id}
- Public API:
- vDelete a user: DELETE /api/users/{id}
#### Note: Deleting a user will result in the deletion of all associated ingredients and dishes.

## Branches:
- spring-security-with-jwt: Branch with Spring Security implementation using JWT for authentication.
- oauth2: Branch with OAuth2 implementation for authentication.

## Clone the repository.
- Select the appropriate branch (spring-security-with-jwt or oauth2).
- Set up your environment and database.
- Run the Spring Boot application.
- Use the APIs to manage the restaurant.
