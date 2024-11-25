package zzjjcc.service;

import io.jcz.annotation.DDSTransactional;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zzjjcc.mapper.maria.eqb.TbB2Mapper;
import zzjjcc.mapper.maria.esb.TbA1Mapper;

@Service
public class CommonCodeToNameService {

    @Resource
    TbA1Mapper tbA1Mapper;

    @Resource
    TbB2Mapper tbB2Mapper;

    @Transactional(rollbackFor = Exception.class)
    public void foo(String content) {
        // 先插入 esb tb_a1 表
        tbA1Mapper.simpleInsertTbA1(">>> foo c1_" + content);
        int i = 1 / 0;
        // 在插入 eqb tb_b2 表
        tbB2Mapper.simpleInsertTbB2(">>> foo c2_" + content);
    }

    @Transactional(rollbackFor = Exception.class)
    public void bar(String content) {
        tbA1Mapper.simpleInsertTbA1("WWW bar c1__" + content);

        CommonCodeToNameService commonCodeToNameService = (CommonCodeToNameService) AopContext.currentProxy();
        commonCodeToNameService.simpleInsertTbB2(content);
        int i = 1 / 0;
    }

    @DDSTransactional
    public void barDDS(String content) {
        tbA1Mapper.simpleInsertTbA1("WWW bar c1__" + content);

        CommonCodeToNameService commonCodeToNameService = (CommonCodeToNameService) AopContext.currentProxy();
        commonCodeToNameService.simpleInsertTbB2(content);
        int i = 1 / 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void bar1(String content) {
        tbA1Mapper.simpleInsertTbA1("WWW bar c1__" + content);
        int i = 1 / 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void simpleInsertTbB2(String content) {
        tbB2Mapper.simpleInsertTbB2("WWW bar c2__" + content);
    }
}
