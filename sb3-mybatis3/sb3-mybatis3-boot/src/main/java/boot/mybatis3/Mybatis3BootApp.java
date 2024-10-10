package boot.mybatis3;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@MapperScan(basePackages = {"boot.mybatis3.mapper"})
public class Mybatis3BootApp implements CommandLineRunner {

    public static final Logger LOG = LoggerFactory.getLogger(Mybatis3BootApp.class);

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(Mybatis3BootApp.class, args);

    }

    @Override
    public void run(String... args) throws SQLException {
        LOG.info("获取数据库连接: {}", dataSource.getConnection());
    }
}
