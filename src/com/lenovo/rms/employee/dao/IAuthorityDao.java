package com.lenovo.rms.employee.dao;

import java.util.List;

import com.lenovo.rms.model.AuthorityGroup;
import com.lenovo.rms.model.AuthorityRole;
import com.lenovo.rms.model.Employee;

public interface IAuthorityDao {
    /**   
    * 查询某一个employee的所有角色
    * @date 2015年3月31日 下午7:38:06   
    * @author Eric   
    * @param employee 待查询的employee
    * @return List<AuthorityRole> 角色列表
    */
    List<AuthorityRole> getAuthorityRoles(Employee employee);
    
    /**   
    * 查询某一个组的所有角色
    * @date 2015年3月31日 下午7:42:50   
    * @author Eric   
    * @param group 待查询的组
    * @return List<AuthorityRole> 角色列表
    */
    List<AuthorityRole> getAuthorityRoles(AuthorityGroup group);
    
    /**   
    * 查询某一个employee的所有组
    * @date 2015年3月31日 下午7:41:34   
    * @author Eric   
    * @param employee 待查询的employee
    * @return List<AuthorityGroup> 组列表  
    */
    List<AuthorityGroup> getAuthorityGroups(Employee employee);
    
    boolean isITLeader(Employee employee);

}
