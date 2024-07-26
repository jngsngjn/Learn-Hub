package project.homelearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HomelearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomelearnApplication.class, args);
    }
}