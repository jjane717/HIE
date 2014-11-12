package org.yijun.hie.controller;

import org.eclipse.jetty.util.ajax.JSON;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.persistence.repository.UserRepository;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import java.util.List;

/**
 * Created by liuyijun on 14-11-8.
 */

@Controller
public class TestController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserRolePrivilegeService userRolePrivilegeService;


    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String helloWorld () {
//        EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
        return "html/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public UserAccountEntity login (String u, String p) {
        return loginService.userLogin(u,p);
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public RoleEntity test () {
        RoleEntity roleEntity = userRepository.getRole("Customer").get(0);
        PrivilegeEntity privilegeEntity = userRepository.getPrivilege("A").get(0);
        userRepository.addPrivilege(roleEntity, privilegeEntity);
        return roleEntity;
    }

    @RequestMapping(value="/tt", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public EnterpriseEntity tt () {
        EnterpriseEntity enterpriseEntity = userRepository.getEnterprise().get(0);
        return enterpriseEntity;
    }

    @RequestMapping(value="/pp", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<PrivilegeEntity> pp () {
        UserAccountEntity userAccountEntity = loginService.userLogin("bbb", "bbbbb");
        List<PrivilegeEntity> privilegeEntityList= userRolePrivilegeService.getParticularPrivileges(userAccountEntity);
        return privilegeEntityList;
    }

    @RequestMapping(value="/oo", method = RequestMethod.GET)
    @ResponseBody
    public JSON oo (JSON o) {
        JSON json = new JSON();

        return json;
    }
}
