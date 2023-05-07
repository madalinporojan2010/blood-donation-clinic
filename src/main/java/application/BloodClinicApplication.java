package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main class, that runs the spring application client.
 */
@SpringBootApplication
public class BloodClinicApplication {
    /**
     * Application main method that initializes the springboot app.
     */
    public static void main(String[] args) {
        SpringApplication.run(BloodClinicApplication.class, args);
    }
}
