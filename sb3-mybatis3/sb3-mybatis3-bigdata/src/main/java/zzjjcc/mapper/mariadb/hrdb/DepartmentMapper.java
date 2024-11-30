package zzjjcc.mapper.mariadb.hrdb;

import org.apache.ibatis.annotations.Param;
import zzjjcc.model.mariadb.hrdb.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> getDepartments();

    String getDepartmentName(@Param("itemName") String itemName,
                             @Param("tableName") String tableName,
                             @Param("dbCodeField") String dbCodeField,
                             @Param("codeValue") String codeValue);

}
