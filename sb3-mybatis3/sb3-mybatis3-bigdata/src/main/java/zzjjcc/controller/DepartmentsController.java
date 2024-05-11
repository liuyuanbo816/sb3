package zzjjcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zzjjcc.common.R;
import zzjjcc.dto.CommonCodeToNameQueryDTO;
import zzjjcc.model.Department;
import zzjjcc.service.DepartmentsService;

import java.util.List;

@RestController
public class DepartmentsController {

    DepartmentsService departmentsServiceImpl;

    @Autowired
    public void setDepartmentsServiceImpl(DepartmentsService departmentsServiceImpl) {
        this.departmentsServiceImpl = departmentsServiceImpl;
    }

    @GetMapping("/getDepartments")
    public List<Department> getDepartments() {
        return departmentsServiceImpl.getDepartments();
    }

    @PostMapping("/getDepartmentsNameById")
    public R<String> getDepartmentsNameById(@RequestBody CommonCodeToNameQueryDTO dto) {
        return R.ok(departmentsServiceImpl.getDepartmentName(dto));
    }

    @PostMapping("/getDepartmentNameWithDTO")
    public R<String> getDepartmentNameWithDTO(@RequestBody CommonCodeToNameQueryDTO dto) {
        return R.ok(departmentsServiceImpl.getDepartmentNameWithDTO(dto));
    }

}
