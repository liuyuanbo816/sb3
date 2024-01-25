package org.zjc.smalltools.controller;

import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zjc.smalltools.common.R;
import org.zjc.smalltools.components.SwitchDataSourceComponent;
import org.zjc.smalltools.mapper.PersonMapper;
import org.zjc.smalltools.model.Person;
import org.zjc.smalltools.vo.request.BaseRequest;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    SwitchDataSourceComponent switchDataSourceComponent;

    @PostMapping("/list")
    public R<List<Person>> list(@Validated @RequestBody BaseRequest baseRequest) {
        try (SqlSession sqlSession = switchDataSourceComponent.getSqlSessionFromRequest(baseRequest)) {
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
            List<Person> personList = personMapper.list();
            return R.ok(personList);
        }
    }

}
