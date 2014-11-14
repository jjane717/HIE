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
        return "html/index.html";
    }


    @RequestMapping(value="/userUpdate", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String update (UserAccountEntity userAccountEntity){
        loginService.updateUserAccount(userAccountEntity);
        return "OK";
    }


    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public String managementSystem (UserAccountEntity userAccountEntity){
        return "html/manage.html";
    }

    @RequestMapping(value = "/manageUserAccounts", method = RequestMethod.GET)
    public String manageUserAccounts (){
        return "html/manageUserAccounts.html";
    }

    @RequestMapping(value = "/manageEmployees", method = RequestMethod.GET)
    public String manageEmployees (){
        return "html/manageEmployees.html";
    }

    @RequestMapping(value = "/manageEnterprises", method = RequestMethod.GET)
    public String manageEnterprises (){
        return "html/manageEnterprises.html";
    }

    @RequestMapping(value = "/manageOffers", method = RequestMethod.GET)
    public String manageOffers (){
        return "html/manageOffers.html";
    }

    @RequestMapping(value = "/manageProducts", method = RequestMethod.GET)
    public String manageProducts (){
        return "html/manageProducts.html";
    }

    @RequestMapping(value = "/viewAccount", method = RequestMethod.GET)
    public String viewAccount (){

        return "html/viewAccount.html";
    }

}
