package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer(){

            @Override
            public void addCorsMapping(CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("GET", "PUT");
            }
        };
    }
}
