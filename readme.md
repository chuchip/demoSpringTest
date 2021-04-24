## Programa para demostrar como testear una aplicación que ataca a una base de datos Mongo.

La URL de conexión a la base de datos debe ser puesta por cada usuario. 

En un principio la  URL configuada en application.properties serviría para conectarse a una base de datos gratuita en Atlas

@See https://cloud.mongodb.com

Si solo se desean probar los tests no es necesario tener una base de datos de mongo en el sistema ya que la applicación lanza una embebida.

Este programa realiza los siguientes tipos de test:

- **DemoSpringTestApplicationTests**  que no usa **Mockito** y es un test completo

  Levanta una instancia de **MongoDB** embebida para realizar las pruebas y realiza las llamadas a nuestro controlador usando **TestRestTemplate**. En este caso las llamadas son reales, es decir se conecta por HTTP y el test es el más completo posible pues pasa por todas las partes de nuestro programa.

- **MockMvcTestApplication**

  Las peticiones web son realizadas a través de **MockMVC** y por lo tanto vamos a la URL del controlador. Se levanta una instancia embebida de **MongoDB**. También es un test completo.

-  **MockitoTestApplication**:

Es el test mas simple y también el más rápido. Usando **Mockito** no se levanta la aplicación de **SpringBoot** ni usa **MongoDB**.  Las peticiones al controlador se hacen llamando a las funciones, por lo cual no se comprueban las URL.

- **MockitoWebTestApplication**:

  Lanza la aplicación entera, es decir se levanta el servidor web pero usamos **Mockito**  para crear el controlador y el repositorio de **MongoDB**. La base de datos en este caso se levanta pero no la usamos ya que mockeamos el repositorio.
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

