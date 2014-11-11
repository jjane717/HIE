package org.yijun.hie.controller;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.util.ajax.JSONObjectConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @RequestMapping(value="/", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String register (HttpServletRequest request) throws ParseException {

        String userName = request.getParameter("username");
        String roleType = "Customer";

        if(loginService.duplicatedUserChecked(userName)){
            loginService.createUserAccount(request,roleType);
            return "success";
        }else{
            return "This User has already been used. User name or email may be duplicated.";
        }
    }
}
