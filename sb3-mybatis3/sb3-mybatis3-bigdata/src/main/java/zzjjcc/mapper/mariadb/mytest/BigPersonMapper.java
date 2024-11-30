package zzjjcc.mapper.mariadb.mytest;

import zzjjcc.model.mariadb.mytest.BigPerson;

import java.util.List;
import java.util.Map;

public interface BigPersonMapper {
    List<BigPerson> lst1(Map<String, Object> param);
}
