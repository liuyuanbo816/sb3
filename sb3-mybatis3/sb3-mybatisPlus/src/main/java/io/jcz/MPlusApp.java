package io.jcz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"io.jcz.mapper"})
public class MPlusApp {
    public static void main(String[] args) {
        SpringApplication.run(MPlusApp.class, args);
    }
}
