package com.lenovo.rms.project.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lenovo.rms.common.dao.impl.HibernateBaseDaoImpl;
import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.dao.IProjectDao;
@Repository("projectDao")
public class ProjectDaoImpl extends HibernateBaseDaoImpl<Project,Long> implements IProjectDao {

    @Override
    public List<Project> getAllProjects() {
        return findAll();
    }

    @Override
    public Project getByProjectNo(String projectNo) {
        Criterion condition = Restrictions.eq("projectNo", projectNo);
        return findUnique(condition);
    }
  
}
