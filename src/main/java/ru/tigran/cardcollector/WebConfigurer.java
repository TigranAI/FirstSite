package ru.tigran.cardcollector;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.SessionTrackingMode;
import java.util.HashSet;

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

    @Bean
    public WebApplicationInitializer webApplicationInitializer(){
        boolean httpsEnable = Boolean.parseBoolean(Config.get("https.enable"));
        return servletContext -> {
            if (!httpsEnable){
                HashSet<SessionTrackingMode> set = new HashSet<>();
                set.add(SessionTrackingMode.COOKIE);
                servletContext.setSessionTrackingModes(set);
            }
        };
    }
}
