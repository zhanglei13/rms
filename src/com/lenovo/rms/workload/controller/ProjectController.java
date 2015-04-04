/**   
* 简述
* <p>详细说明第一行<br>    
* 详细说明第二行 
* @date 2015年4月3日 下午8:39:00   
* @author zhanglei   
* @version V1.0   
*/

package com.lenovo.rms.workload.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.rms.model.Project;
import com.lenovo.rms.workload.service.IProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	/**
	 * logger:创建日志logger
	 */
	protected static Logger logger = Logger.getLogger(ProjectController.class);
	
	@Autowired
	private IProjectService projectService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}
}
