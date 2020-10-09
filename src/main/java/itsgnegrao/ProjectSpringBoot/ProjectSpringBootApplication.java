package itsgnegrao.ProjectSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

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

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(API_URL_LOCAL, API_URL));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
//                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin",
//                "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
