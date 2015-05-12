package com.lenovo.rms.employee.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.employee.dao.IOrgnizationDao;
import com.lenovo.rms.model.AuthorityRole;
import com.lenovo.rms.model.Organization;

public class OrgnizationDaoImpl  extends HibernateBaseDaoImpl implements IOrgnizationDao{

	@Override
	public Organization getByItCode(String itCode) {
		  String hql="select org from Employee e, Organization org where e.itCode=:itCode and org.orgCode=e.orgCode";
	       Map<String,String> params = new HashMap<String,String>();
	       params.put("itCode", itCode);
	       @SuppressWarnings("unchecked")
	       List<Organization> orgs= findHql(hql,params);
	       return orgs.get(0);
	}

}
