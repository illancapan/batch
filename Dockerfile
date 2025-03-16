# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Crear y establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR construido en el contenedor
COPY build/libs/batch-demo-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación Spring Boot correrá
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
