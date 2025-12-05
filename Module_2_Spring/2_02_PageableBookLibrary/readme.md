# Pageable Library
## Task
Implement a service with CRUD operations using Spring Data JPA.
Make sure to use sorting and pagination.

Endpoints to implement:
- Get all books (with pagination and ability to sort by a field)
- Get a particular book
- Add a new book
- Update a book
- Delete a book

Additionally, it is necessary to implement exception handling
returning appropriate HTTP statuses and error messages.

## Launch
At first, you would need to create/update `.env` file.
An example of an env file:
```
# APPLICATION CONFIGURATION
APP_PORT=8080
APP_PORT_INTERNAL=8080

# DATABASE CONFIGURATION
DB_HOST=book-library-db
DB_NAME=book_library_db
DB_PORT=5433
DB_PORT_INTERNAL=5432
DB_USER=user
DB_PASSWORD=password
DB_URL=jdbc:postgresql://${DB_HOST}:${DB_PORT_INTERNAL}/${DB_NAME}
```

To launch the application run:
```shell
docker-compose build
docker-compose up -d
```