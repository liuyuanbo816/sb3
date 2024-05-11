package zzjjcc.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.dto.CommonCodeToNameQueryDTO;
import zzjjcc.mapper.maria.DepartmentMapper;
import zzjjcc.model.Department;
import zzjjcc.service.DepartmentsService;

import java.util.List;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {

    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }

    @Override
    public String getDepartmentName(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO) {
        return departmentMapper.getDepartmentName(
                commonCodeToNameQueryDTO.getItemName(),
                commonCodeToNameQueryDTO.getTableName(),
                commonCodeToNameQueryDTO.getDbCodeField(),
                commonCodeToNameQueryDTO.getCodeValue());
    }

    @Override
    public String getDepartmentNameWithDTO(CommonCodeToNameQueryDTO commonCodeToNameQueryDTO) {
        return departmentMapper.getDepartmentNameWithDTO(commonCodeToNameQueryDTO);
    }
}
