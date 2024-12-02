package io.jcz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"io.jcz.mapper"})
public class App9001 {
    public static void main(String[] args) {
        SpringApplication.run(App9001.class, args);
    }
}
