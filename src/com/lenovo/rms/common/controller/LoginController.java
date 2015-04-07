package com.lenovo.rms.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lenovo.rms.common.service.ILoginService;
import com.lenovo.rms.common.util.Constants;
import com.lenovo.rms.common.util.IPUtils;

/**
 * 
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年4月1日 下午2:19:22
 * @author zhanglei
 * @version V1.0
 */
@Controller
@RequestMapping
public class LoginController {

    protected static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session, Model model) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        String info = "Login failed!";
        String failure = "/login";
        if (name.isEmpty())
            info = "Please input your username!";
        else if (password.isEmpty())
            info = "Please input your password!";
        else {
            String ip = IPUtils.getRemoteHost(request);
            if (loginService.login(name, password, ip, session)) {
                info = "login success";
                model.addAttribute("info", info);
                model.addAttribute("itCode",name);
                return "redirect:/index";
            }
        }

        model.addAttribute("info", info);
        return failure;
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
    @RequestMapping(value = "/logout", produces = "text/html;charset=UTF-8")
    public String logOut(HttpSession session, Model model) {
        if (session.getAttribute(Constants.SESSION_USERINFO_KEY) != null) {
            session.removeAttribute(Constants.SESSION_USERINFO_KEY);
        }
        model.addAttribute("info", "Logout!");
        return "/login";
    }
}
