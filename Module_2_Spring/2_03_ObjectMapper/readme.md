# Object Mapper
## Task
The task was to implement a system to manage orders in an online store with the
explicit usage of Jackson's `ObjectMapper` class. So, the JSON has to be mapped
and validated programmatically. 

### Entities
- `ProductEntity` contains name, description, price and quantity in store
- `CustomerEntity` contains customer's info - name, contact number and email
- `OrderEntity` describes an order of a customer: date, address, list of items ordered

### Endpoints
- `GET /api/v1/products` - get the list of all products
- `POST /api/v1/products` - create a new product
- `PATCH /api/v1/products/{productId}` - update the info of a particular product
- `DELETE /api/v1/products/{productId}` - delete the info about a particular product
- `POST /api/v1/orders` - create a new order
- `GET /api/v1/orders/{orderId}` - get info about a certain order

## Launch
First, create/edit a `.env` file:
```shell
# APP
APP_PORT=8080
APP_PORT_INTERNAL=8080

# DB
DB_PORT=5433
DB_PORT_INTERNAL=5432
DB_NAME=object_mapper_db
DB_HOST=object-mapper-db
DB_USER=secret
DB_PASSWORD=secret
DB_URL=jdbc:postgresql://${DB_HOST}:${DB_PORT_INTERNAL}/${DB_NAME}
```

Then you would be able to launch the application with:
```shell
docker-compose build
docker-compose up -d
```