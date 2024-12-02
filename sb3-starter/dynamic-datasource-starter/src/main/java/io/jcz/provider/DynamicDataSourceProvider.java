package io.jcz.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 多数据源加载接口
 */
public interface DynamicDataSourceProvider {

    Map<String, DataSource> loadDataSources();

}
