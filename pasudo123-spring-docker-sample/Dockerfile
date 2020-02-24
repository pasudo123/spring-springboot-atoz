# 도커를 실행하기 위한 jre 만 필요
# jdk 는 자바를 개발하기 위함 개발 키트이기 때문에 상관 무
FROM openjdk:8-jre
COPY target/springdockerapp-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]