package com.lenovo.rms.common.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.rms.common.util.Constants;

/**
 * 
* 简述
* <p>详细说明第一行<br>    
* 详细说明第二行 
* @date 2015年4月1日 下午2:19:22   
* @author zhanglei   
* @version V1.0
 */
@Controller
@RequestMapping
public class BaseController {

	private static Logger logger = Logger.getLogger(BaseController.class);

	@RequestMapping("/login")
	public String login(HttpSession session, Model model) {
		String info="Login failed";
		boolean flag=false;
		String url="/workload";
//		if(user.getAccount().isEmpty()||user.getPassword().isEmpty()){
//			info="用户名和密码不能为空";
//		}
//		else {
//			if(true){
//				info="登入成功";
//				flag=true;
//				url="redirect:/index";
//			}
//		}
		model.addAttribute("info",info);
		model.addAttribute("flag", flag);
		return url;
	}

	/**
	 * 
	 * 名称：to500 <br/>
	 * 描述：500错误跳转 <br/>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/to500", produces = "text/html;charset=UTF-8")
	public String to500() {
		return "/error/500";
	}

	/**
	 * 
	 * 名称：to404 <br/>
	 * 描述：404错误跳转 <br/>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/to404", produces = "text/html;charset=UTF-8")
	public String to404() {
		return "/error/404";
	}

	/**
	 * 
	 * 名称：index <br/>
	 * 描述：登入成功后跳到首页 <br/>
	 * 
	 * @return String
	 */
	@RequestMapping("/index")
	public String index() {
		return "/index";
	}

	/**
	 * 
	 * 名称：logOut <br/>
	 * 描述：登入注销 <br/>
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/logOut", produces = "text/html;charset=UTF-8")
	public String logOut(HttpSession session, Model model) {
		if (session.getAttribute(Constants.SESSION_USERINFO_KEY) != null) {
			session.removeAttribute(Constants.SESSION_USERINFO_KEY);
		}
		model.addAttribute("info", "用户已成功登出！");
		model.addAttribute("flag", true);
		return "/tologin";
	}
}
