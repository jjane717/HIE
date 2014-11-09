package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.LoginService;

/**
 * Created by liuyijun on 14-11-8.
 */

@Controller
public class TestController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String helloWorld () {
//        EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
        return "html/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    @ResponseBody
    public UserAccountEntity login (String u, String p) {
        return loginService.userLogin(u, p);
    }
}
