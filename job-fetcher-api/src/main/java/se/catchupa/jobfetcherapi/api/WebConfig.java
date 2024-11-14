package se.catchupa.jobfetcherapi.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allows CORS for all endpoints
                        .allowedOrigins("http://localhost:3000") // Allows only this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allows these HTTP methods
                        .allowedHeaders("*"); // Allows all headers
            }
        };
    }
}
