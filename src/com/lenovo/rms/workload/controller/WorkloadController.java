package com.lenovo.rms.workload.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import com.lenovo.rms.common.util.JsonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lenovo.rms.common.util.Constants;
import com.lenovo.rms.common.util.TimeUtils;
import com.lenovo.rms.model.Employee;
import com.lenovo.rms.workload.model.WorkloadRow;
import com.lenovo.rms.workload.service.IWorkloadService;

/**
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年4月3日 下午8:39:00
 * @author zhanglei
 * @version V1.0
 */

@Controller
public class WorkloadController {
	/**
	 * logger:创建日志logger
	 */
	protected static Logger logger = Logger.getLogger(WorkloadController.class);
	
	@Autowired
	private IWorkloadService workloadService;
	
	@RequestMapping("/workload/list")
	@ResponseBody
	public List<WorkloadRow> listWorkloadRows(String itCode) {
		return workloadService.listWorkloadRows(itCode);
	}
	
	@RequestMapping(value = "/workload/save", method = RequestMethod.POST)
	@ResponseBody
	public void saveWorkloads(@RequestParam("workloadRows") String workloadRowsString,@RequestParam("itCode") String itCode) {
	     List<WorkloadRow> workloadRows = JsonUtils.jsonList2JavaList(workloadRowsString, WorkloadRow.class);
		 workloadService.saveWorkloads(workloadRows, itCode);
	}
	
	@RequestMapping("/add")
    public String mdAdd() {
        return "/mdadd";
    }
}
