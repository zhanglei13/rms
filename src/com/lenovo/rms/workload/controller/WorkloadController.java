package com.lenovo.rms.workload.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.rms.workload.model.WorkloadRow;
import com.lenovo.rms.workload.service.IWorkloadService;

/**   
* 简述
* <p>详细说明第一行<br>    
* 详细说明第二行 
* @date 2015年4月3日 下午8:39:00   
* @author zhanglei   
* @version V1.0   
*/

@Controller
@RequestMapping("/workload")
public class WorkloadController {
	/**
	 * logger:创建日志logger
	 */
	protected static Logger logger = Logger.getLogger(WorkloadController.class);
	
	@Autowired
	private IWorkloadService workloadService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<WorkloadRow> listWorkloadRows(String itCode) {
		return workloadService.listWorkloadRows(itCode);
	}
	
	@RequestMapping("/save")
	public void saveWorkloads(List<WorkloadRow> workloadRows, String itCode) {
		workloadService.saveWorkloads(workloadRows, itCode);
	}
}
