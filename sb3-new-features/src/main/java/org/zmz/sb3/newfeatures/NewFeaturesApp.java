package org.zmz.sb3.newfeatures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NewFeaturesApp {
    public static void main(String[] args) {
        SpringApplication.run(NewFeaturesApp.class,args);
    }
}
