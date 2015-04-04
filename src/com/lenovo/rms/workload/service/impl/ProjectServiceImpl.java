package com.lenovo.rms.workload.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lenovo.rms.model.Project;
import com.lenovo.rms.workload.dao.IProjectDao;
import com.lenovo.rms.workload.service.IProjectService;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService{

    // 开启日志
    protected static Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    @Autowired
    protected IProjectDao projectDao;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 100)
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

}
