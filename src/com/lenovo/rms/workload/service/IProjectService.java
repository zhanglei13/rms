package com.lenovo.rms.workload.service;

import java.util.List;

import com.lenovo.rms.model.Project;

public interface IProjectService {
    /**   
     * 获取所有的project信息
     * @date 2015年4月2日 下午4:18:02   
     * @author Eric   
     * @return
     * List<Project>  所有Project列表
     */
     List<Project> getAllProjects();

     /**
      * 
     * 根据id获得project  
     * @author zhanglei   
     * @param projectNo
     * @return
     * Project
      */
     Project getByProjectNo(String projectNo);
}
