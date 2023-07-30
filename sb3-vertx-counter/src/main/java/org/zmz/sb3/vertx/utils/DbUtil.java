package org.zmz.sb3.vertx.utils;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DbUtil {
    private DbUtil() {
    }

    private static DbUtil dbUtil;


    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @PostConstruct
    public void init() {
        dbUtil = new DbUtil();
        dbUtil.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public static long getUserId() {
        Long res = dbUtil.getSqlSessionTemplate().selectOne("userMapper.getUserId");
        return res == null ? -1L : res;
    }
}
