package org.yijun.hie.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import org.eclipse.jetty.util.ajax.JSON;
import org.hibernate.SessionFactory;
import org.hibernate.internal.jaxb.cfg.ObjectFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.persistence.repository.UserRepository;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by liuyijun on 14-11-8.
 */

@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
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

//    @RequestMapping(value="/po", method = RequestMethod.GET)
//    @ResponseBody
//    @Transactional
//    public List<PrivilegeEntity> po (){
//        UserAccountEntity userAccountEntity = loginService.userLogin("bbb", "bbbbb");
//        List<PrivilegeEntity> privilegeEntityList= userRolePrivilegeService.getParticularPrivileges(userAccountEntity);
////        JSONObject jsonObject = new JSONObject();
////
////        for (PrivilegeEntity privilegeEntity:privilegeEntityList){
////            jsonObject.put(privilegeEntity.getIdPrivilege().toString(),privilegeEntity.getPrivilegeName());
////        }
////
////        System.out.println(jsonObject);
//        return privilegeEntityList;
//    }

//    @RequestMapping(value="/oo", method = RequestMethod.GET)
//    @Transactional
//    public String oo (HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        UserAccountEntity userAccountEntity = loginService.userLogin("aaa", "aaaaa");
//        List<PrivilegeEntity> privilegeEntityList= userRolePrivilegeService.getParticularPrivileges(userAccountEntity);
//        List list = new LinkedList();
//        for (PrivilegeEntity privilegeEntity:privilegeEntityList){
//            list.add(privilegeEntity.getPrivilegeName());
//        }
//        session.setAttribute("test",list);
//
//        return "html/test";
//    }


    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    @Transactional
    public String manageOffers(){
        return "html/hello.html";
    }

    @RequestMapping(value = "/testjsp", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate );

        return "html/test";

    }
}
