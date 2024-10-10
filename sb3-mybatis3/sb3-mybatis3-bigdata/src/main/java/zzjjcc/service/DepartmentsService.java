package zzjjcc.service;

import zzjjcc.dto.CommonCodeToNameQueryDTO;
import zzjjcc.model.Department;

import java.util.List;

public interface DepartmentsService {
    List<Department> getDepartments();

    String getDepartmentName(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO);

    String getDepartmentNameWithDTO(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO);
}
