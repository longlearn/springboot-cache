package com.czl.springbootcache.service;

import com.czl.springbootcache.bean.Employee;
import com.czl.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * ClassName:EmpSevice
 * Package:com.czl.springbootcache.service
 * Description:
 *
 * @date:2020-3-4 10:59
 * @autor:18855032359
 */

/**
 * CacheConfig：总体配置
 * 在类上配置之后，方法上就不需要配置了
 */
@CacheConfig(cacheNames = "emp")
@Service
public class EmpSevice {
    @Autowired()
    EmployeeMapper employeeMapper;
    public int addEmployee(Employee employee) {
        return employeeMapper.addEmp(employee);
    }

    /**
     * @CacheEvict:清除缓存
     * key: 指定要清除的缓存
     * allEntries = true  -->清除这个缓存的所有数据
     * beforeInvocation = true  -->缓存的清除是否再执行方法之前
     * 当值为false,则当方法报错时，不会清除缓存
     * 值为true,方法即使报错，也会清缓存
     *
     * @param id
     * @return
     */
    @CacheEvict(/*value = "emp",*/key = "#id"/*allEntries = true*/,beforeInvocation = true  )
    public int deleteEmployee(Integer id) {
        System.out.println("伪装删除id为"+id+"的员工");
//        employeeMapper.deleteEmp(id);
        int i = 10/0;
        return id;
    }


    /**
     *
     * @Cacheable
     * 将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中取，不用再调用方法了
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp")
    public Employee findById(Integer id) {
        System.out.println("查询id为 "+id +"的员工");
        return employeeMapper.findById(id);
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据
     * 修改了数据库的某个数据，同时更新缓存
     * @param employee
     */
    @CachePut(/*cacheNames = "emp",*/key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("修改id为 "+employee.getId() +"的员工");
        employeeMapper.updateEmp(employee);
        return employee;
    }

    @Caching(
            cacheable = {
                @Cacheable(/*value = "emp",*/key = "#lastName")
            },
            put = {
                    @CachePut(/*cacheNames = "emp",*/key = "#result.id"),
                    @CachePut(/*cacheNames = "emp",*/key = "#result.email")
            }
    )
    public Employee findByLastName(String lastName) {
        System.out.println("查询lastName为 "+lastName +"的员工");
        return employeeMapper.findByLastName(lastName);
    }

}
