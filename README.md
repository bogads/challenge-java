# Challenge Java

Este proyecto es una aplicación desarrollada en Java utilizando **Jakarta EE**, **Spring Boot**, **Spring Data JPA**, y **Spring MVC**, con soporte para tests de integración y unitarios.

## Requisitos

Antes de iniciar, asegúrate de cumplir con los siguientes requisitos:

- **Java 17**
- **Maven**
- **Base de datos** (PostgreSQL, MySQL, H2 u otra recomendable en tu caso). Para este challenge se uso h2

## Instalación

1. Clona el repositorio:

   ```bash
   git clone <URL-del-repositorio>
   cd <nombre-del-proyecto>
   ```

2. Configura el archivo `application.properties` con las credenciales de tu base de datos:

   ```properties
   spring.application.name=challenge
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.hibernate.ddl-auto=update
   spring.h2.console.enabled=true
   user.eamil.regex=^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.cl$
   jwt.secret=testaplicationchallengejwtsecretneedtobeabiggerkey
   ```

3. Compila el proyecto usando Maven:

   ```bash
   mvn clean install
   ```

4. Inicia la aplicación:

   ```bash
   mvn spring-boot:run
   ```

## Uso

La aplicación expone un endpoint REST. Aquí algunos ejemplos básicos:

### Crear un Usuario

**POST** `/api/v1/users`

Request Body:

```json
{
   "name": "string",
   "email": "sebastia2n@dominio.cl",
   "password": "string",
   "phones": [
      {
         "number": "string",
         "cityCode": "string",
         "countryCode": "string"
      }
   ]
}
```

Response:

```json
{
   "id": "51255c36-b78b-4032-b890-06039e474594",
   "created": "2024-12-15T01:23:28.878764",
   "modified": "2024-12-15T01:23:28.878786",
   "last_login": "2024-12-15T01:23:28.864969",
   "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZWJhc3RpYTJuQGRvbWluaW8uY2wiLCJpYXQiOjE3MzQyMzY2MDgsImV4cCI6MTczNDI0MDIwOH0.TZmRqj75dK5GaCPSYDxlMeFth01NozNMKSnfQnajCMo",
   "isActive": true
}
```
## Pruebas

Este proyecto incluye pruebas unitarias y de integración. Los tests se encuentran en las siguientes ubicaciones:

- **Unitarias**: `src/test/java/com/bogado/sebastian/challenge/service/impl/UserServiceImplTest.java`
- **Integración**: `src/test/java/com/bogado/sebastian/challenge/integration/UserControllerIntegrationTest.java`

Ejecuta las pruebas con Maven:

```bash
mvn test
```

## Estructura del Proyecto

El proyecto sigue una estructura típica para aplicaciones Spring Boot:
Consta en service, controller, exception, repository, y model.

![Diagrama del Proyecto](diagrama.png)