>> Requerimientos

- JDK 11
- IntelliJ IDEA 2021.1.3 o Spring Tool Suite
- Git Bash
- Postman
- DBeaver u otra herramienta para acceder a base de datos.
- PostgreSQL


* Crear la base de datos transpselva en POSTGRES en impactar los scripts que se ubican en la carpeta 'database'.

- access.client.sql


* Actualizar el fichero de properties 'server-properties/transpselva-access.properties'.


* Abrir Git Bash y ejecutar el script para compilar los proyectos.

sh build.sh


* Ejecutar los proyectos

1. Abrir una terminal cmd en la carpeta 'artifacts' y ejecutar.

java -jar -DMINIUBER_PATH_BASE="/synopsis/fuentes/mini-uber" server-config.jar

2. Abrir una terminal cmd en la carpeta 'artifacts' y ejecutar.

java -jar -DURL_SERVER_CONFIG=http://localhost:8888 transpselva-access.jar


* Escenarios de prueba.

TEST
----

>> Comparar la salida del siguiente comando:

curl --location --request GET 'http://localhost:8888/transpselva-access/master' > test/response/server-properties.test.curl

- Salida debe ser igual al contenido de 'test/response/server-properties.curl'.


>> Comparar la salida del siguiente comando:

curl --location --request POST 'http://localhost:10001/login/v1/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: Cookie_2=value' \
--data-raw '{
    "email": "elvisperez.tec@gmail.com",
    "environment": "M",
    "appVersion": "1.0",
    "password": "123456"
}' > test/response/apiaccess.test.curl

- Salida debe tener el mismo formato de 'test/response/apiaccess.curl'.


>> Implementar los siguientes endpoints.

loadAll: GET 'http://localhost:10001/login/v1/clients'
findById: POST 'http://localhost:10001/login/v1/clients/{clientId}'