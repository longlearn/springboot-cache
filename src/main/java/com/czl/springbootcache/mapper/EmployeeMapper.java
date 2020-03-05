package com.czl.springbootcache.mapper;

import com.czl.springbootcache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * ClassName:EmployeeMapper
 * Package:com.czl.springbootcache.mapper
 * Description:
 *
 * @date:2020-3-4 10:44
 * @autor:18855032359
 */

public interface EmployeeMapper {
    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    int addEmp(Employee employee);
    @Delete("delete employee where id = #{id}")
    int deleteEmp(Integer id);
    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    void updateEmp(Employee employee);

    @Select("SELECT * FROM employee WHERE id = #{id}")
    Employee findById(Integer id);

    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    Employee findByLastName(String lastName);
}
