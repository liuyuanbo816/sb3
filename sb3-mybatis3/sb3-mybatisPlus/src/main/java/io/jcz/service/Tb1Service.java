package io.jcz.service;

import io.jcz.mapper.Tb1Mapper;
import io.jcz.model.Tb1;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Tb1Service {

    @Resource
    private Tb1Mapper tb1Mapper;

    @Resource
    SqlSessionFactory sqlSessionFactory;

    public List<Tb1> findAll() {
        return tb1Mapper.selectList(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<Tb1> list) {
        tb1Mapper.batchInsert(list);
        if ("EXO".equals(list.getFirst().getName())) {
            throw new RuntimeException("1111 模拟发生异常,测试手动事务回滚");
        }
    }

    public void batchInsert2(List<Tb1> list) {
        SqlSession sqlSession = this.getSqlSession();
        try {
            Tb1Mapper mapper = sqlSession.getMapper(Tb1Mapper.class);
            mapper.batchInsert(list);
            if ("EXO".equals(list.getFirst().getName())) {
                throw new RuntimeException("2222 模拟发生异常,测试手动事务回滚");
            }
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
