package zzjjcc.service.mariadb.hrdb;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.mariadb.hrdb.DepartmentMapper;

@Service
public class DepartmentsService {
    @Resource
    DepartmentMapper departmentMapper;
}
