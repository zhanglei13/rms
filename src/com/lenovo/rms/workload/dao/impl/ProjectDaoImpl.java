package com.lenovo.rms.workload.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.workload.dao.IProjectDao;
@Repository("projectDao")
public class ProjectDaoImpl extends HibernateBaseDaoImpl<Project,Long> implements IProjectDao {

    @Override
    public List<Project> getAllProjects() {
        return findAll();
    }
  
}
