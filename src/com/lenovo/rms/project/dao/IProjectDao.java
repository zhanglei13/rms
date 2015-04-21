package com.lenovo.rms.project.dao;

import java.util.List;

import com.lenovo.rms.model.Project;

public interface IProjectDao {
    List<Project> getAllProjects();
    
    Project getByProjectNo(String projectNo);

    List<String> getByItLeader(String itCode);
    
    List<String> getProjectMembers(String projectNo);
}
