package ru.tigran.cardcollector;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {

        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("file:" + "./resources/static/");
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/database/logs");
                registry.addMapping("/sticker");
                registry.addMapping("/collection");
            }
        };
    }
}
