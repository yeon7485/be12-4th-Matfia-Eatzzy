FROM openjdk:17-jdk-slim

WORKDIR /app
# 작업 디렉토리

COPY ./build/libs/gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
# 포트 노출 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
# 실행 명령어, 필요 시 JVM 옵션 추가 가능