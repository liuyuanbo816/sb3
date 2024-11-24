package zzjjcc.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionContext {

    /**
     * 开启事务
     */
    public static void doStartTx(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
    }

    /**
     * 提交事务
     */
    public static void doCommitTx(Connection connection) throws SQLException {
        connection.commit();
    }

    /**
     * 回滚事务
     */
    public static void doRollbackTx(Connection connection) throws SQLException {
        connection.rollback();
    }
}
