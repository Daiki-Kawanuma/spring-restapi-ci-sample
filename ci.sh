mvn test
mvn --skip-test spring-boot:build-image
docker-compose up -d
docker logs test-driver
docker-compose down