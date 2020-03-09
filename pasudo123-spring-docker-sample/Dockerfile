# 도커를 실행하기 위한 jre 만 필요
# jdk 는 자바를 개발하기 위함 개발 키트이기 때문에 상관 무

# FROM : 이미지를 만들기 위한 베이스가 되는 이미지 설정
# gradlew
FROM openjdk:8-jre-alpine

WORKDIR /volume1/docker/deploy/www/masterpiece

ENTRYPOINT ["java", "-jar", "/spring-docker-1.0.0.jar"]


EXPOSE 8080

### [ADD] Command
# Syntax : ADD {source} {destination}
# 파일, 디렉토리를 Docker 이미지로 복사하는데 사용
# 로컬 저장소에서 도커 이미지의 대상으로 파일을 복사
# gradle 기준으로 settings.gradle 에 있는 rootProject.name = 'spring-docker' 을 따른다.