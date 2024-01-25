package org.zjc.smalltools.components;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurationWrapper extends Configuration {
    private Configuration configuration;

    private Environment environment;

    private Map<String, MappedStatement> idMapperStatementMap = new ConcurrentHashMap<>();

    public ConfigurationWrapper(Configuration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public MapperRegistry getMapperRegistry() {
        return configuration.getMapperRegistry();
    }

    @Override
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return configuration.getMapperRegistry().getMapper(type, sqlSession);
    }

    @Override
    public boolean hasStatement(String statementName, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            buildAllStatements();
        }
        return configuration.getMappedStatementNames().contains(statementName);
    }

    @Override
    public MappedStatement getMappedStatement(String id, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            buildAllStatements();
        }
        MappedStatement cacheMappedStatement = idMapperStatementMap.get(id);
        if (cacheMappedStatement != null) {
            return cacheMappedStatement;
        } else {
            Optional<MappedStatement> mappedStatementOptional = configuration.getMappedStatements().stream()
                    .filter(mappedStatement -> mappedStatement.getId().equals(id)).findFirst();
            mappedStatementOptional.ifPresent(mappedStatement -> idMapperStatementMap.put(id, mappedStatement));
            return mappedStatementOptional.orElse(null);
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Map<String, MappedStatement> getIdMapperStatementMap() {
        return idMapperStatementMap;
    }

    public void setIdMapperStatementMap(Map<String, MappedStatement> idMapperStatementMap) {
        this.idMapperStatementMap = idMapperStatementMap;
    }
}