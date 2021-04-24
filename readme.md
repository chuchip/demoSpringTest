## Programa para demostrar como testear una aplicación que ataca a una base de datos Mongo.

La URL de conexión a la base de datos debe ser puesta por cada usuario. 

En un principio la  URL configuada en application.properties serviría para conectarse a una base de datos gratuita en Atlas

@See https://cloud.mongodb.com

Si solo se desean probar los tests no es necesario tener una base de datos de mongo en el sistema ya que la applicación lanza una embebida.

Este programa realiza los siguientes tests:

- Un test completo en la clase "DemoSpringTestApplicationTests" que no usa Mockito. Usa DB mongodb embebida 
- Test simple usando Mockito en la clase  MockitoTestApplication. No se lanza la aplicacion web en entorno web ni usa Mongodb
- Test lanzando la aplicacion web usando mockito en MockitoWebTestApplication. No usa mongodb.
- Test lanzando la aplicacion web. Se realizan las llamadas a traves de MockMVC. No usa mongodb.

En el código explico las diferentes anotaciones.

Más documentación en: https://spring.io/guides/gs/testing-web/

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

- helloWorld

curl --location --request GET 'http://localhost:8080/helloWorld'

