# Guía para Levantar la API "Prices"

Estos son los comandos básicos para poder ejecutar la API "Prices"

## Tecnologías usadas

- JAVA 17
- SPRING BOOT 3.1.4
- MAVEN
- H2
- JUNIT
- MockMvc
- GIT
- SWAGGER

## Inicialización automática de la base de datos H2

La base de datos h2 se inicializa automáticamente al ejecutar la aplicación. Puede encontrar los script de creación y inicialización de la base de datos en la carpeta resources a través de los archivos [schema.sql](/src/main/resources/schema.sql) y [data.sql](/src/main/resources/data.sql)

Se ha añadido también una version del script de creación de la base de datos donde se implementa la relación de la tabla "Brand" con la tabla "Prices". El script es [dbextra.sql](dbextra.sql)

## Ejemplos de ejecución de la función search

Para más información se puede consultar la documentación de Swagger

```
applicationDate -> 2020-06-14-10.00.00
productId -> 35455
brandId -> 1
```

## Instalar dependencias y crear WAR

```bash
mvn install
```

## Levantar la API

```bash
mvn spring-boot:run
```

## Acceder a Swagger

Se ha añadido la interfaz Swagger-ui para poder probar todos los métodos de la API

[Enlace a Swagger](http://localhost:8080/swagger-ui/index.html#)


## Ejecutar los test

```bash
mvn test
```

