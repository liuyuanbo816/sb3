package zzjjcc.service.mariadb.hrdb;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.mariadb.hrdb.EmployeeMapper;

@Service
public class EmployeeService {
    @Resource
    EmployeeMapper employeeMapper;
}
