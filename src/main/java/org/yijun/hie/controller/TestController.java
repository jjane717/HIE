package org.yijun.hie.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.persistence.repository.UserRepository;
import org.yijun.hie.service.LoginService;

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

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public RoleEntity test () {
        RoleEntity roleEntity = userRepository.getRole("Customer").get(0);
        PrivilegeEntity privilegeEntity = userRepository.getPrivilege("A").get(0);
        userRepository.addPrivilege(roleEntity, privilegeEntity);
        return roleEntity;
    }
}
