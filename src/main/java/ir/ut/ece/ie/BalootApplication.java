package ir.ut.ece.ie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BalootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BalootApplication.class);
        log.info("http://localhost:8080/swagger-ui.html");
        log.info("To authenticate using github: http://localhost:8080/oauth2/authorization/github");
    }
}
