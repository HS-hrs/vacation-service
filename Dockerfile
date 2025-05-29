# 1. OpenJDK 17 기반 이미지 사용
FROM openjdk:17

# 2. 작업 디렉토리 지정
WORKDIR /app

# 3. 빌드된 JAR 파일 복사 (경로 주의!)
COPY build/libs/vacation-service-0.0.1-SNAPSHOT.jar app.jar

# 4. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]