# Usando a imagem base do OpenJDK 22
FROM openjdk:22-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR para o contêiner
COPY target/*.jar app.jar

# Copie o arquivo .env (opcional, se precisar dentro do contêiner)
COPY .env .env

# Exponha a porta do aplicativo
EXPOSE 8081

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
