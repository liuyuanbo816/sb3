package org.zjc.smalltools.mapper;

import org.apache.ibatis.annotations.Select;
import org.zjc.smalltools.model.Person;

import java.util.List;

public interface PersonMapper {
    @Select("select * from tb_person")
    List<Person> list();
}
