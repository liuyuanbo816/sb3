package io.jcz.transaction;

import org.springframework.core.NamedThreadLocal;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionMultiConnectionContext {

    private static final ThreadLocal<Map<String, Connection>> MULTI_CONNECTION =
            NamedThreadLocal.withInitial(ConcurrentHashMap::new);

    /**
     * 放入数据源
     *
     * @return 返回true表明第一次放, 返回false表明之前已经放过
     */
    public static Boolean putConnection(String ddsName, Connection connection) {
        Map<String, Connection> connectionMap = MULTI_CONNECTION.get();
        if (!connectionMap.containsKey(ddsName)) {
            connectionMap.put(ddsName, connection);
            return true;
        }
        return false;
    }

    /**
     * 获取数据源链接
     */
    public static Connection getConnection(String ddsName) {
        return MULTI_CONNECTION.get().get(ddsName);
    }

    /**
     * 事务内所有连接 提交
     */
    public static void commit() throws Exception {
        Map<String, Connection> connectionMap = MULTI_CONNECTION.get();
        try {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                Connection connection = entry.getValue();
                if (connection != null) {
                    TransactionContext.doCommitTx(connection);
                }
            }
        } finally {
            MULTI_CONNECTION.remove();
        }
    }

    /**
     * 事务内所有连接回滚
     */
    public static void rollback() throws Exception {
        Map<String, Connection> connectionMap = MULTI_CONNECTION.get();
        try {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                Connection connection = entry.getValue();
                if (connection != null) {
                    TransactionContext.doRollbackTx(connection);
                }
            }
        } finally {
            MULTI_CONNECTION.remove();
        }
    }
}
