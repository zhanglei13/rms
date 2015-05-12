package com.lenovo.rms.employee.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.employee.dao.IAuthorityDao;
import com.lenovo.rms.model.AuthorityGroup;
import com.lenovo.rms.model.AuthorityRole;
import com.lenovo.rms.model.Employee;
@Repository("authorityDao")
public class AuthorityDaoImpl extends HibernateBaseDaoImpl implements IAuthorityDao{

    @Override
    public List<AuthorityRole> getAuthorityRoles(Employee employee) {
        String hql="select r from AuthorityRole r, AuthorityGroupRoleRef roleRef, AuthorityEmployeeGroupRef groupRef where groupRef.itCode=:itCode and groupRef.groupCode=roleRef.groupCode and roleRef.roleCode=r.roleCode";
        Map<String,String> params = new HashMap<String,String>();
        params.put("itCode", employee.getItCode());
        @SuppressWarnings("unchecked")
        List<AuthorityRole> roles= findHql(hql,params);
        return roles;
    }

    @Override
    public List<AuthorityRole> getAuthorityRoles(AuthorityGroup group) {
        String hql="select r from AuthorityRole r, AuthorityGroupRoleRef ref where ref.groupCode=:groupCode and ref.roleCode=r.roleCode";
        Map<String,String> params = new HashMap<String,String>();
        params.put("groupCode", group.getGroupCode());
        @SuppressWarnings("unchecked")
        List<AuthorityRole> roles= findHql(hql,params);
        return roles;
    }
    
	@Override
	public boolean isITLeader(Employee employee) {
		List<AuthorityRole> roles = getAuthorityRoles(employee);
		for(AuthorityRole role:roles){
			if(role.getRoleName().equals("IT Leader")){
				return true;
			}
		}
		return false;
	}
    
    
    
    @Override
    public List<AuthorityGroup> getAuthorityGroups(Employee employee) {
        String hql="select g from AuthorityGroup g, AuthorityEmployeeGroupRef ref where ref.itCode=:itCode and ref.groupCode=g.groupCode";
        Map<String,String> params = new HashMap<String,String>();
        params.put("itCode", employee.getItCode());
        @SuppressWarnings("unchecked")
        List<AuthorityGroup> groups= findHql(hql,params);
        return groups;
    }
    public static void main(String[] args){
       
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
        System.out.println("start");
        IAuthorityDao dao = ctx.getBean(AuthorityDaoImpl.class);
        Employee employee =new Employee();
        employee.setItCode("acashwell");
       /* List<AuthorityGroup> groups = dao.getAuthorityGroups(employee);
        for(AuthorityGroup group:groups){
            System.out.println(group.getGroupName());
        }*/
        List<AuthorityRole> roles = dao.getAuthorityRoles(employee);
        for(AuthorityRole role:roles){
            System.out.println(role.getRoleName());
        }
        System.out.println("end");
 
    }


}
