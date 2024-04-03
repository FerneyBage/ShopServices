package services.shop;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainersConfig {
    PostgreSQLContainer<?> postgreSQLContainer(){
        return new PostgreSQLContainer<>("postgres:15-alpine");
    }
}
