package org.yijun.hie.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
//import org.eclipse.jetty.util.ajax.JSON;
import org.hibernate.SessionFactory;
import org.hibernate.internal.jaxb.cfg.ObjectFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.persistence.repository.UserRepository;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
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
    public UserAccountEntity login () {
        return loginService.userLogin();
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

    @RequestMapping(value="/testJsp", method = RequestMethod.GET)
    @Transactional
    public ModelAndView testJsp (HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("key", "Hello world!");
        List<String> testList = new LinkedList<String>();
        testList.add("A");
        testList.add("B");
        testList.add("C");
        testList.add("D");
        testList.add("E");
        modelAndView.addObject("list", testList);
        return modelAndView;
    }

    @RequestMapping(value="/pp", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String pp () throws JSONException {
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<PrivilegeEntity> privilegeEntityList= userRolePrivilegeService.getParticularPrivileges(userAccountEntity);
        JSONObject jsonObject = new JSONObject();

        for (PrivilegeEntity privilegeEntity:privilegeEntityList){
            jsonObject.put(privilegeEntity.getPrivilegeName(),privilegeEntity.getPrivilegeFile());
        }
        System.out.println(jsonObject);
        return jsonObject.toString();
    }

//    @RequestMapping(value="/po", method = RequestMethod.GET)
//    @ResponseBody
//    @Transactional
//    public List<PrivilegeEntity> po (){
//        UserAccountEntity userAccountEntity = loginService.userLogin();
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

    @RequestMapping(value="/oo", method = RequestMethod.GET)
    @Transactional
    public String oo (Model uiModel) {

        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<PrivilegeEntity> privilegeEntityList= userRolePrivilegeService.getParticularPrivileges(userAccountEntity);

        uiModel.addAttribute("privilegeEntityList",privilegeEntityList);
        uiModel.addAttribute("key","Yijun Liu");
        List<String> list = new LinkedList<String>();

        list.add("one");
        list.add("two");

        uiModel.addAttribute("pp", list);

        return "test";
    }


    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    @Transactional
    public String manageOffers(){
        return "html/hello.html";
    }
}
