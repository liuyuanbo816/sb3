package zzjjcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"zzjjcc.mapper"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class BigDataComponentApp {
    public static void main(String[] args) {
        SpringApplication.run(BigDataComponentApp.class, args);
    }
}