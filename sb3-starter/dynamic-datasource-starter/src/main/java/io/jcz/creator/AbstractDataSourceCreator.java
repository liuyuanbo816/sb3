package io.jcz.creator;


import io.jcz.init.DataSourceInit;
import io.jcz.propterties.DataSourceProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDataSourceCreator implements DataSourceCreator {

    @Autowired
    private List<DataSourceInit> dataSourceInits;

    @Override
    public DataSource createDataSource(DataSourceProperty dataSourceProperty) {
        Optional.ofNullable(dataSourceInits).stream().flatMap(Collection::stream)
                .sorted(Comparator.comparing(DataSourceInit::getOrder)).
                forEach(init -> init.beforeCreate(dataSourceProperty));
        DataSource dataSource = doCreateDataSource(dataSourceProperty);
        Optional.ofNullable(dataSourceInits).stream().flatMap(Collection::stream)
                .sorted(Comparator.comparing(DataSourceInit::getOrder)).
                forEach(init -> init.afterCreate(dataSourceProperty));
        return dataSource;
    }

    protected abstract DataSource doCreateDataSource(DataSourceProperty dataSourceProperty);
}
