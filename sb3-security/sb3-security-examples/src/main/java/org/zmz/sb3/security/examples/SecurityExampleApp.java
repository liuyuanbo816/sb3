package org.zmz.sb3.security.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.zmz.sb3.security"})
public class SecurityExampleApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityExampleApp.class,args);
    }
}
