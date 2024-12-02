package io.jcz.service;

import io.jcz.mapper.Tb1Mapper;
import io.jcz.model.Tb1;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Tb1Service {

    @Resource
    private Tb1Mapper tb1Mapper;

    public List<Tb1> findAll() {
        return tb1Mapper.selectList(null);
    }

}
