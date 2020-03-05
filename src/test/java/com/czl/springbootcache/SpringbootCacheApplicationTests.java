package com.czl.springbootcache;

import com.czl.springbootcache.bean.Employee;
import com.czl.springbootcache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootCacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Test
    void contextLoads() {
        Employee employee = employeeMapper.findById(1);
        System.out.println(employee);
    }

}
