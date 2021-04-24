## Programa para demostrar como testear una aplicación que ataca a una base de datos Mongo.

La URL de conexión a la base de datos debe ser puesta por cada usuario. 

En un principio esta URL serviria para conectarse a una base de datos gratuita en Atlas

@See https://cloud.mongodb.com

Si solo se desean probar los tests no es necesario tener una base de datos de mongo en el sistema ya que la applicación lanza una embebida.

Este programa realiza un test completo en la clase "DemoSpringTestApplicationTests" y otro usando Mockito en la clase  MockitoTestApplication

Los controladores existentes son:

- Insert document.

curl --location --request POST 'localhost:8080/add' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"firstName": "Jesus",
"lastName": "Javier"
}'

- getAllDocument

curl --location --request GET 'http://localhost:8080/'