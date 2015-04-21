package com.lenovo.rms.project.dao;

import java.util.List;

import com.lenovo.rms.model.Project;

public interface IProjectDao {
    List<Project> getAllProjects();
    
    Project getByProjectNo(String projectNo);
    
    List<Project> getOwnedProjects(String itCode);
}
