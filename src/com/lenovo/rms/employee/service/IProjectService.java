package com.lenovo.rms.employee.service;

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

}
