package zzjjcc.transaction;

import org.springframework.util.StringUtils;

import java.util.UUID;

public class TransactionUtil {

    /**
     * 开启事务
     */
    public static void startTransaction() {
        String txGlobalId = TransactionGlobalIdContext.getTxGlobalId();
        if (!StringUtils.hasText(txGlobalId)) {
            txGlobalId = UUID.randomUUID().toString();
            TransactionGlobalIdContext.setTxGlobalId(txGlobalId);
        }
    }

    /**
     * 事务内所有连接 提交
     */
    public static void commit() throws Exception {
        TransactionMultiConnectionContext.commit();
    }

    public static void rollback() throws Exception {
        TransactionMultiConnectionContext.rollback();
    }
}
