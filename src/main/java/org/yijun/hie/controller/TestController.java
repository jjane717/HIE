package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
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
    public ProductEntity test (String u, String p) {
        EnterpriseEntity enterpriseEntity = userRepository.getEnterprise().get(0);
        ProductEntity productEntity = enterpriseEntity.getProductEntityList().get(0);
        return productEntity;
    }
}
