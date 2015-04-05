package com.lenovo.rms.workload.dao;

import java.util.List;

import com.lenovo.rms.model.Project;

public interface IProjectDao {
    List<Project> getAllProjects();
    
    Project getByProjectNo(String projectNo);
}
