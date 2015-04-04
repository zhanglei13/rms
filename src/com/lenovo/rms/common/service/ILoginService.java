package com.lenovo.rms.common.service;

import javax.servlet.http.HttpSession;

public interface ILoginService {
     boolean login(String username,String password,String ip,HttpSession session);

}
