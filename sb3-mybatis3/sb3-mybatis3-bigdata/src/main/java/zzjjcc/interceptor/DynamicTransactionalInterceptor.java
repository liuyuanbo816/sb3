package zzjjcc.interceptor;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import zzjjcc.transaction.TransactionGlobalIdContext;
import zzjjcc.transaction.TransactionUtil;

@Slf4j
public class DynamicTransactionalInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String txGlobalId = TransactionGlobalIdContext.getTxGlobalId();
        //表示我们之前已经开启了事务 (txGlobalId代表全局有没有开启事务)
        if (StringUtils.isNotEmpty(txGlobalId)) {
            return invocation.proceed();
        }
        //开启事务
        TransactionUtil.startTransaction();
        Object result;
        try {
            //执行目标方法
            result = invocation.proceed();
        } catch (Exception e) {
            log.error("事务执行失败>>> ", e);
            //执行目标方法失败 事务回滚
            TransactionUtil.rollback();
            throw e;
        }
        //提交事务
        TransactionUtil.commit();
        return result;
    }
}