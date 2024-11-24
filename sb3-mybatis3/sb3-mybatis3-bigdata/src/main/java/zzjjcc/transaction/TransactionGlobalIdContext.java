package zzjjcc.transaction;

/**
 * 全局事务id管理上下文
 */
public class TransactionGlobalIdContext {
    private static final ThreadLocal<String> TX_GLOBAL_ID = new ThreadLocal<>();

    /**
     * 获取全局事务id
     *
     * @return
     */
    public static String getTxGlobalId() {
        return TX_GLOBAL_ID.get();
    }

    public static void setTxGlobalId(String txId) {
        TX_GLOBAL_ID.set(txId);
    }

    public static void removeGlobalId() {
        TX_GLOBAL_ID.remove();
    }
}
