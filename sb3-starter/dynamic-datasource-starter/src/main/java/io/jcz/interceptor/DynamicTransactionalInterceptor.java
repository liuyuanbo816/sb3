
package io.jcz.interceptor;

import io.jcz.transaction.TransactionGlobalIdContext;
import io.jcz.transaction.TransactionUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

public class DynamicTransactionalInterceptor implements MethodInterceptor {

    public static final Logger LOG = LoggerFactory.getLogger(DynamicTransactionalInterceptor.class);

    @Override
    public Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
        String txGlobalId = TransactionGlobalIdContext.getTxGlobalId();
        //表示我们之前已经开启了事务 (txGlobalId代表全局有没有开启事务)
        if (StringUtils.hasText(txGlobalId)) {
            return invocation.proceed();
        }
        //开启事务
        TransactionUtil.startTransaction();
        Object result;
        try {
            //执行目标方法
            result = invocation.proceed();
        } catch (Exception e) {
            LOG.error(">>> 事务执行失败 >>> ", e);
            //执行目标方法失败 事务回滚
            TransactionUtil.rollback();
            throw e;
        }
        //提交事务
        TransactionUtil.commit();
        return result;
    }
}
