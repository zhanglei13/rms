package com.lenovo.rms.project.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.rms.model.Project;
import com.lenovo.rms.project.dao.IProjectDao;
import com.lenovo.rms.project.service.IProjectService;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService{

    // 开启日志
    protected static Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    @Autowired
    protected IProjectDao projectDao;
    
    @Override
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    @Override
    public Project getByProjectNo(String projectNo) {
        return projectDao.getByProjectNo(projectNo);
    }

}
