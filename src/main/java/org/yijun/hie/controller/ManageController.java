package org.yijun.hie.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

/**
 * Created by liuyijun on 14-11-12.
 */

@Controller
public class ManageController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserRolePrivilegeService userRolePrivilegeService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public UserAccountEntity viewAccount (){
        UserAccountEntity userAccountEntity = loginService.userLogin("admin", "admin");

        return userAccountEntity;
    }
}
