package io.jcz.datasource;

import io.jcz.transaction.TransactionContext;
import io.jcz.transaction.TransactionGlobalIdContext;
import io.jcz.transaction.TransactionMultiConnectionContext;
import io.jcz.util.DynamicDataSourceContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.SmartDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractRoutingDataSource extends AbstractDataSource implements SmartDataSource {

    @Override
    public Connection getConnection() throws SQLException {
        //没有事务
        String txGlobalId = TransactionGlobalIdContext.getTxGlobalId();
        if (!StringUtils.hasText(txGlobalId)) {
            return determineDataSource().getConnection();
        } else {
            //有事务的情况下
            String currentDataSource = DynamicDataSourceContextHolder.getCurrentDataSource();
            Connection connection = TransactionMultiConnectionContext.getConnection(currentDataSource);
            if (connection == null) {
                connection = determineDataSource().getConnection();
                Boolean putFirst = TransactionMultiConnectionContext.putConnection(currentDataSource, connection);
                if (putFirst) {
                    TransactionContext.doStartTx(connection);
                }
            }
            return connection;
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        //没有事务
        String txGlobalId = TransactionGlobalIdContext.getTxGlobalId();
        if (!StringUtils.hasText(txGlobalId)) {
            return determineDataSource().getConnection(username, password);
        } else {
            //有事务的情况下
            String currentDataSource = DynamicDataSourceContextHolder.getCurrentDataSource();
            Connection connection = TransactionMultiConnectionContext.getConnection(currentDataSource);
            if (connection == null) {
                connection = determineDataSource().getConnection(username, password);
                Boolean putFirst = TransactionMultiConnectionContext.putConnection(currentDataSource, connection);
                if (putFirst) {
                    TransactionContext.doStartTx(connection);
                }
            }
            return connection;
        }
    }

    @Override
    public boolean shouldClose(Connection con) {
        String txGlobalId = TransactionGlobalIdContext.getTxGlobalId();
        if (StringUtils.hasText(txGlobalId)) {
            //获取当前数据源的连接
            String currentDataSource = DynamicDataSourceContextHolder.getCurrentDataSource();
            Connection connection = TransactionMultiConnectionContext.getConnection(currentDataSource);
            return connection != con;
        }
        return true;
    }

    /**
     * 通过子类来获取具体的数据源
     */
    protected abstract DataSource determineDataSource();
}
