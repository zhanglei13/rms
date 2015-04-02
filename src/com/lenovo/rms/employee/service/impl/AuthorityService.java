package com.lenovo.rms.employee.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.rms.employee.dao.IAuthorityDao;
import com.lenovo.rms.employee.service.IAuthorityService;
import com.lenovo.rms.model.AuthorityRole;
import com.lenovo.rms.model.Employee;
@Service("authorityService")
public class AuthorityService implements IAuthorityService {

    // 开启日志
    protected static Logger logger = Logger.getLogger(AuthorityService.class);

    @Autowired
    protected IAuthorityDao authorityDao;
    @Override
    public List<AuthorityRole> getAuthorityRoles(Employee employee) {

        return authorityDao.getAuthorityRoles(employee);
    }

}
