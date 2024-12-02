package zzjjcc.mapper.mariadb.hrdb;

import org.apache.ibatis.annotations.Param;
import zzjjcc.model.mariadb.hrdb.Employee;

import java.util.List;

public interface EmployeeMapper {
    Employee queryEmployeeById(@Param("id") int id);

    void insertEmployee(Employee employee);

    void updateEmployee(@Param("id") int id);

    void deleteEmployee(@Param("id") int id);

    List<Employee> getEmployees();
}
