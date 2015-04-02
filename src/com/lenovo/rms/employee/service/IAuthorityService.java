package com.lenovo.rms.employee.service;

import java.util.List;

import com.lenovo.rms.model.AuthorityRole;
import com.lenovo.rms.model.Employee;

public interface IAuthorityService {
    /**
     *获取employee的所有权限信息
     * @date 2015年4月2日 下午4:31:16
     * @author Eric
     * @param employee 要获取权限的employee
     * @return List<AuthorityRole> 获取的权限列表
     */
    List<AuthorityRole> getAuthorityRoles(Employee employee);
}
