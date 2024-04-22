# Mini Restaurant
This project implements a set of REST APIs for simplified restaurant management, using Java Spring Boot and Spring Security with JWT and OAuth2 for authentication and authorization.

## Technical Specifications:

Restaurant Registration:
- Endpoint: POST /api/restaurant
- Parameters: id, email, restaurant name
### OAuth2 Login:
- Endpoint: POST /api/login
- Parameters: email, password
### Authenticated User-only APIs:
- Create a new ingredient: POST /home/aggiungi-ingrediente
- Create a new dish: POST /home/aggiungi-piatto
- Get the list of ingredients: GET /home/lista-ingredienti
- Get the list of dishes: GET /home/lista-piatti
- Delete an ingredient: DELETE /home/elimina-ingrediente
- Delete a dish: DELETE /home/elimina-piatto
- Public API:
- vDelete a user: DELETE localhost:8081/auth/delete-utente
#### Note: Deleting a user will result in the deletion of all associated ingredients and dishes.

## Branches:
- spring-security-with-jwt: Branch with Spring Security implementation using JWT for authentication.
- oauth2: Branch with OAuth2 implementation for authentication.

## Clone the repository.
- Select the appropriate branch (spring-security-with-jwt or oauth2).
- Set up your environment and database.
- Run the Spring Boot application.
- Use the APIs to manage the restaurant.
