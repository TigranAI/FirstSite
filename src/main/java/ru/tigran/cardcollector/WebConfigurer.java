package ru.tigran.cardcollector;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Component
public class WebConfigurer {
    private final String stickerPath = "/stickers/**";
    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                if (!registry.hasMappingForPattern(stickerPath)) {
                    registry.addResourceHandler(stickerPath)
                            .addResourceLocations("file:" + "resources/stickers/");
                }
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
