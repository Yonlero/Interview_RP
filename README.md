# Interview_RP

Service API for RP Interview

# Endpoints Client

- GET - http://localhost:8080/rp/clients ( Get all clients )
- GET - http://localhost:8080/rp/clients/{id} ( Get an client by Id )
- POST - http://localhost:8080/rp/clients/create ( Create a new album )

# Endpoints Order

- GET - http://localhost:8080/rp/orders ( Get all clients )
- GET - http://localhost:8080/rp/orders/pendents ( Get all pendents orders )
- GET - http://localhost:8080/rp/orders/{id} ( Get an orders by Id )
- POST - http://localhost:8080/rp/orders/create ( Create a new order )
- PUT - http://localhost:8080/rp/orders/update ( Update a existent order )
- PATCH - http://localhost:8080/rp/orders/updatestatus/{id} ( Create a order status )

# Swagger Documentation Endpoint

- http://localhost:8080/rp/swagger-ui

# Postman File/Collection

- You can use the postman collection called: Interview_RP.postman_collection.json

# Example of Body Request

- Example Post Client request body
    ```curlrc
        curl http://localhost:8080/rp/client/create \ 
        --include --header "Content-Type: application/json" \ 
        --request "POST" \
        --data '{ \
             "name": "Test_1_Post", \
             "cellphone": "22922222222", \
             "email": "testingPost@testing.com", \
             "address": { \
                 "number": "11", \
                 "district": "Test_District_Post", \
                 "street": "Test_Street_Post", \
                 "city": "Test_City_Post" \
                 } \
            }'
    ```
- Example Example Post Order request body
    ```curlrc
        curl http://localhost:8080/albums \ 
        --include --header "Content-Type: application/json" \ 
        --request "PUT" \
        --data '{ \
            "client": { \
                  "id": "e91dcb40-bef8-4e65-b16e-b334211e0731" \
            }, \
            "equipments": [ \
                { \
                     "type": "Eletronic", \
                     "brand": "Samsung" \
                }, \
                { \
                     "type": "Eletronic", \
                     "brand": "Xiaomi" \
                } \
            ], \
            "responsible": "Tec_1", \
            "problem_description": "Equipments don't turn on", \
            "solution_description": "", \
            "order_problems": "" \
          } \
        }'

```