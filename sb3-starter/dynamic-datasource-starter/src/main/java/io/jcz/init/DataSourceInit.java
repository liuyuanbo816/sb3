package io.jcz.init;

import io.jcz.propterties.DataSourceProperty;

/**
 * 切入数据源创建生命周期
 */
public interface DataSourceInit {

    /**
     * 创建之前允许做一些事情，比如用户名密码进行解密
     */
    void beforeCreate(DataSourceProperty dataSourceProperty);

    /**
     * 数据源创建之后允许做一些事情
     */
    void afterCreate(DataSourceProperty dataSourceProperty);

    default int getOrder() {
        return 0;
    }
}
