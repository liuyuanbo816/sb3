package bilibili.v1.ping;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DataSourcePing {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourcePing.class);

    DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() throws SQLException {
        LOG.info("初始化数据库连接");
        LOG.info("数据库连接: {}", dataSource.getConnection());
    }

}
