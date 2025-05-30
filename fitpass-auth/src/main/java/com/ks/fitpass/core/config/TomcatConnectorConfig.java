package com.ks.fitpass.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class TomcatConnectorConfig {
    // Redirect HTTP to HTTPS
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
        return tomcat;
    }

    // Configure the connector to redirect HTTP to HTTPS
    private Connector httpToHttpsRedirectConnector() {
        log.info("Redirecting to 443");
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080); // The HTTP port
        connector.setSecure(false);
        connector.setRedirectPort(443); // The HTTPS port
        return connector;
    }
}
