package ru.tigran.cardcollector;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HttpsConfigurer {
    @Bean
    public ServletWebServerFactory servletContainer() {
        boolean httpsEnable = Boolean.parseBoolean(Config.get("https.enable"));
        TomcatServletWebServerFactory tomcat;
        if (httpsEnable) {
            tomcat = new TomcatServletWebServerFactory() {
                @Override
                protected void postProcessContext(Context context) {
                    SecurityConstraint securityConstraint = new SecurityConstraint();
                    securityConstraint.setUserConstraint("CONFIDENTIAL");
                    SecurityCollection collection = new SecurityCollection();
                    collection.addPattern("/*");
                    securityConstraint.addCollection(collection);
                    context.addConstraint(securityConstraint);
                }
            };
            tomcat.addAdditionalTomcatConnectors(httpRedirect());
        } else {
            tomcat = new TomcatServletWebServerFactory();
            tomcat.addAdditionalTomcatConnectors(httpConnector());
        }
        return tomcat;
    }

    private Connector httpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(Integer.parseInt(Config.get("server.port")));
        return connector;
    }

    private Connector httpRedirect() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(Integer.parseInt(Config.get("server.port")));
        connector.setSecure(false);
        connector.setRedirectPort(Integer.parseInt(Config.get("server.https.port")));
        return connector;
    }
}
