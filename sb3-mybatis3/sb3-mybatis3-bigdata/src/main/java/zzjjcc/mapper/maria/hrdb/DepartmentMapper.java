package zzjjcc.mapper.maria.hrdb;

import org.apache.ibatis.annotations.Param;
import zzjjcc.dto.CommonCodeToNameQueryDTO;
import zzjjcc.model.Department;

import java.util.List;

public interface DepartmentMapper {
    List<Department> getDepartments();

    String getDepartmentName(@Param("itemName") String itemName,
                             @Param("tableName") String tableName,
                             @Param("dbCodeField") String dbCodeField,
                             @Param("codeValue") String codeValue);

    String getDepartmentNameWithDTO(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO);
}
