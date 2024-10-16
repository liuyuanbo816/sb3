package zzjjcc.mapper.pgsql;

import org.apache.ibatis.annotations.Param;
import zzjjcc.model.Employee;

import java.util.List;

public interface EmployeeMapper {
    Employee queryEmployeeById(@Param("id") int id);

    void insertEmployee(Employee employee);

    void updateEmployee(@Param("id") int id);

    void deleteEmployee(@Param("id") int id);

    List<Employee> getEmployees();
}
