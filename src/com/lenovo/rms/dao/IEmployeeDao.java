package com.lenovo.rms.dao;

import com.lenovo.rms.common.dao.IHibernateBaseDao;
import com.lenovo.rms.model.Employee;

public interface IEmployeeDao{
     Employee findEmployeeByName(String name);
}
