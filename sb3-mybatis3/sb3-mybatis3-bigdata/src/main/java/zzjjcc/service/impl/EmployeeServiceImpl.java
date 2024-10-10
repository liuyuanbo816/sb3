package zzjjcc.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import org.springframework.stereotype.Service;
import zzjjcc.mapper.pgsql.EmployeeMapper;
import zzjjcc.model.Employee;
import zzjjcc.service.EmployeeService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public Employee queryEmployeeById(int id) {
        return employeeMapper.queryEmployeeById(id);
    }

    @Override
    public void insertEmployee(Employee employee) {

    }

    @Override
    public void updateEmployee(int id) {

    }

    @Override
    public void deleteEmployee(int id) {

    }

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }

    @Override
    public void downloadExcel(ServletOutputStream outputStream) {
        EasyExcelFactory.write(outputStream, Employee.class)
                .sheet("User")
                .doWrite(this::getUserList);
    }

    private List<Employee> getUserList() {
        return Collections.singletonList(
                Employee.builder()
                        .employeeId(1)
                        .firstName("firstName")
                        .lastName("lastName")
                        .email("micbobo816@gmail.com")
                        .phoneNumber("15273379271")
                        .hireDate(LocalDate.now())
                        .jobId("1")
                        .salary(10000.00)
                        .commissionPct(1.1)
                        .managerId(1)
                        .departmentId(1)
                        .build()
        );
    }
}
