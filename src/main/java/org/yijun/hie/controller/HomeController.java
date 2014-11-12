package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * Created by liuyijun on 14-11-10.
 */

@Controller
public class HomeController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String mainIndex () {
        return "html/index";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public String register (UserAccountEntity userAccountEntity) throws ParseException {

//        String userName = request.getParameter("userName");
//        String roleType = "Customer";

        if(!loginService.isUserExist(userAccountEntity.getUserName())) {

            loginService.createUserAccount(userAccountEntity);
            return "success";
        } else {
            return "This User has already been used. User name or email may be duplicated.";
        }
    }

    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public String managementSystem (UserAccountEntity userAccountEntity){
        return "html/manage";
    }
}
