package org.zjc.smalltools.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zjc.smalltools.mapper.PersonMapper;
import org.zjc.smalltools.model.Person;

@Service
public class FooService {

    @Resource
    PersonMapper personMapper;

    @Transactional(rollbackFor = Exception.class)
    public void foo1(Person p) {
        personMapper.insert(p);
        int i = 1 / 0;
    }

}
