# Account Service

## Descripción

El **Account Service** es un microservicio desarrollado en Java con Spring Boot, encargado de gestionar las cuentas bancarias y los movimientos financieros dentro de una arquitectura de microservicios. Este servicio permite realizar operaciones CRUD sobre cuentas y movimientos, y también se integra con Apache Kafka para recibir actualizaciones sobre clientes desde el `customer-service`.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**: Para la persistencia en la base de datos MySQL.
- **MySQL**: Base de datos relacional utilizada para almacenar la información de las cuentas y movimientos.
- **Apache Kafka**: Utilizado para la mensajería entre microservicios.
- **Lombok**: Para reducir el código boilerplate.
- **Maven**: Gestión de dependencias y construcción del proyecto.

## Prerrequisitos

Antes de ejecutar este proyecto, asegúrate de tener instalado lo siguiente:

- **Java 17** o superior
- **Apache Maven 3.6** o superior
- **MySQL** (o una base de datos compatible)
- **Apache Kafka** y **Zookeeper**

## Configuración

### Configuración de la Base de Datos

1. Crea una base de datos en MySQL para el `account-service`:

   ```sql
   CREATE DATABASE accountdb;
