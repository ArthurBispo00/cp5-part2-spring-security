# --- Estágio 1: Build (Compilação) ---
# Usamos uma imagem Maven com Java 17 para compilar o projeto
FROM maven:3.9.8-eclipse-temurin-17-focal AS build

# Define o diretório de trabalho dentro da imagem
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .

# Baixa as dependências (isso só roda se o pom.xml mudar)
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Compila o projeto e gera o .jar. -DskipTests pula os testes.
RUN mvn clean package -DskipTests


# --- Estágio 2: Run (Execução) ---
# Usamos uma imagem Java 17 leve (sem Maven) para rodar a aplicação
FROM eclipse-temurin:17-jre-focal

# Define o diretório de trabalho
WORKDIR /app

# Copia APENAS o .jar compilado do estágio de build para a imagem final
# Isso torna a imagem final muito menor!
COPY --from=build /app/target/mercado-express-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 (a porta padrão do Spring Boot)
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner ligar
# Usamos a variável de ambiente PORT que o Render fornece
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]