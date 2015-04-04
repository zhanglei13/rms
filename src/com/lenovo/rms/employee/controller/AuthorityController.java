package com.lenovo.rms.employee.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.rms.employee.service.IAuthorityService;
import com.lenovo.rms.model.AuthorityRole;
import com.lenovo.rms.model.Employee;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	/**
	 * logger:创建日志logger
	 */
	protected static Logger logger = Logger.getLogger(AuthorityController.class);
	
	@Autowired
	private IAuthorityService authorityService;
	
	@RequestMapping("/roles")
	@ResponseBody
	public List<AuthorityRole> getAuthorityRoles(String itCode) {
		return authorityService.getAuthorityRoles(itCode);
	}
}
