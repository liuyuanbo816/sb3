package io.jcz.creator;


import io.jcz.propterties.DataSourceProperty;

import javax.sql.DataSource;

/**
 * 数据源创建接口
 */
public interface DataSourceCreator {

    /**
     * 通过属性创建数据源
     */
    DataSource createDataSource(DataSourceProperty dataSourceProperty);

    /**
     * 当前创建器是否支持根据此属性创建
     */
    boolean support(DataSourceProperty dataSourceProperty);
}
