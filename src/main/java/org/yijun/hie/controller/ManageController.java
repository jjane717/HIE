package org.yijun.hie.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import java.util.List;

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

    @RequestMapping(value="/enterprise", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<EnterpriseEntity> getEnterpriseList () {
        return loginService.getEnterpriseList();
    }

    @RequestMapping(value="/enterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public EnterpriseEntity enterpriseOne (String id) {
        Integer idEnterprise = Integer.valueOf(id);
        return loginService.getEnterpriseOneFromService(idEnterprise);
    }

    @RequestMapping(value="/updateEnterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String updateEnterprise (EnterpriseEntity enterpriseEntity) {
        loginService.updateEnterpriseFromService(enterpriseEntity);
        return "OK";
    }

    @RequestMapping(value="/addEnterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public EnterpriseEntity addEnterprise (EnterpriseEntity enterpriseEntity) {
        return loginService.addEnterpriseFromService(enterpriseEntity);
    }

    @RequestMapping(value="/deleteEnterprise", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String deleteEnterprise (String id) {
        Integer idEnterprise = Integer.valueOf(id);
        EnterpriseEntity enterpriseEntity = loginService.getEnterpriseOneFromService(idEnterprise);
        loginService.deleteEnterpriseFromService(enterpriseEntity);
        return "You have already Delete this Enterprise.";
    }

    @RequestMapping(value="/userAccountForEnterprise", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<UserAccountEntity> getUserAccountsByEnterprise(){
        UserAccountEntity userAccountEntity = loginService.userLogin("admin", "admin");

        List<UserAccountEntity> userAccountEntityList =  loginService.getUserAccountsByEnterpriseFromService(userAccountEntity.getEnterpriseEntity());
        return userAccountEntityList;
    }
}
