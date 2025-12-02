Практическое задание - Spring MVC - json view

# Json View

## Task
The task is to implement a RESTful application for 
managing user information and their respective orders.
It is necessary to use `@JsonView` annotation in order to produce
the json with appropriate data, e.g. show user's orders in a detailed view,
but hide them otherwise.

### Entities to create:
- `User` - contains the information about the user - id, name, email, etc...
- `Order` - describes an order and contains the information about the products, total amount and status.


### Endpoints to create:
- Get all user's information (without details)
- Get particular user's information (with details)
- Create a new user
- Update an existing user
- Delete an existing user

Other:
- Implement exception handling with the use of correct HTTP status in every case
- Validate incoming data (using `jakarta.validation`)
- Create unit-tests to test that different json views implemented correctly

## Launch
To configure the application edit the `.env` file.

To launch the application use the following commands:
```shell
docker-compose build
docker-compose up -d
```