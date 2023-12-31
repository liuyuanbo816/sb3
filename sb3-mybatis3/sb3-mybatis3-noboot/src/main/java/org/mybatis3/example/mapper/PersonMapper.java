package org.mybatis3.example.mapper;

import org.mybatis3.example.pojo.Person;

import java.util.List;

public interface PersonMapper {
    Person getPersonById(Long id);

    List<Person> getPersons();
}