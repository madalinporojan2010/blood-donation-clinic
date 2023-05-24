package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Application main class, that runs the spring application client.
 */
@SpringBootApplication
public class BloodClinicApplication implements WebMvcConfigurer {
    /**
     * Application main method that initializes the springboot app.
     */
    public static void main(String[] args) {
        SpringApplication.run(BloodClinicApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("POST", "PUT", "GET",
                        "DELETE");
            }
        };
    }
}
