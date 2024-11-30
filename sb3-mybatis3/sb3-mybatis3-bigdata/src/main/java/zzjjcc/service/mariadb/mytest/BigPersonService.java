package zzjjcc.service.mariadb.mytest;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.mariadb.mytest.BigPersonMapper;

@Service
public class BigPersonService {

    @Resource
    BigPersonMapper bigPersonMapper;

}
