FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests -q
FROM eclipse-temurin:17-jre-alpine

LABEL maintainer="your-team@college.edu"
LABEL app="grocery-inventory"
LABEL version="1.0.0"

WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

COPY --from=builder /app/target/grocery-inventory.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser


EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD wget -qO- http://localhost:8080/api/items/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]