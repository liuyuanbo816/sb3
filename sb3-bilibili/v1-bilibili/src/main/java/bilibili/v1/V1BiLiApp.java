package bilibili.v1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "bilibili.v1.mapper")
public class V1BiLiApp {
    public static void main(String[] args) {
        SpringApplication.run(V1BiLiApp.class, args);
    }
}
