package com.czl.springbootcache.controller;

import com.czl.springbootcache.bean.Employee;
import com.czl.springbootcache.service.EmpSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:empController
 * Package:com.czl.springbootcache.controller
 * Description:
 *
 * @date:2020-3-4 11:10
 * @autor:18855032359
 */
@RestController
public class empController {
    @Autowired
    EmpSevice empSevice;

    @RequestMapping(value = "/emp/{id}")
    public Employee empInfoById(@PathVariable("id") Integer id) {
        return empSevice.findById(id);
    }
    @GetMapping(value = "/emp")
    public Employee updateEmp(Employee employee) {
        return empSevice.updateEmp(employee);
    }
    @GetMapping("/deleteEmp")
    public String deleteEmp(Integer id) {
        empSevice.deleteEmployee(id);
        return "deleteEmp Success";
    }
    @GetMapping("/emp/lastname/{lastName}")
    public Employee employee (@PathVariable("lastName") String lastName) {
        return empSevice.findByLastName(lastName);
    }
}
