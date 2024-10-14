# Usar una imagen base de OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
COPY target/prices-0.0.1-SNAPSHOT.jar /app/prices.jar


# Exponer el puerto en el que corre Spring Boot (generalmente 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/prices.jar"]
