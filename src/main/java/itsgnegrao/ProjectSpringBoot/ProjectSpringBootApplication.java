package itsgnegrao.ProjectSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static itsgnegrao.ProjectSpringBoot.configs.ConfigsFrontEnd.API_URL;
import static itsgnegrao.ProjectSpringBoot.configs.ConfigsFrontEnd.API_URL_LOCAL;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@ComponentScan
public class ProjectSpringBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProjectSpringBootApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(API_URL_LOCAL, API_URL);
                registry.addMapping("/api/**").allowedOrigins(API_URL_LOCAL, API_URL);
                registry.addMapping("/source").allowedOrigins(API_URL_LOCAL, API_URL);
                registry.addMapping("/api/client").allowedOrigins(API_URL_LOCAL, API_URL);
                registry.addMapping("/api/client/**").allowedOrigins(API_URL_LOCAL, API_URL);
            }
        };
    }

}
