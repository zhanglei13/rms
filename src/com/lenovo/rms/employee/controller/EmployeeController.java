package com.lenovo.rms.employee.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.rms.employee.service.IEmployeeService;
import com.lenovo.rms.model.Employee;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	/**
	 * logger:创建日志logger
	 */
	protected static Logger logger = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/get")
	@ResponseBody
	public Employee getEmployeeByItCode(String itCode) {
		return employeeService.getEmployeeByItCode(itCode);
	}
}
