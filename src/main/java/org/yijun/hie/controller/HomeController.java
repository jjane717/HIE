package org.yijun.hie.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.EnterpriseProductEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * Created by liuyijun on 14-11-10.
 */

@Controller
public class HomeController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private ProductController productController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainIndex() {
        return "html/index.html";
    }


    @RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String update(UserAccountEntity userAccountEntity) {
        loginService.updateUserAccount(userAccountEntity);
        return "OK";
    }


    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public String managementSystem(UserAccountEntity userAccountEntity) {
        return "manage";
    }

    @RequestMapping(value = "/manageUserAccounts", method = RequestMethod.GET)
    public String manageUserAccounts() {
        return "manageUserAccounts";
    }

    @RequestMapping(value = "/manageEmployees", method = RequestMethod.GET)
    public String manageEmployees() {
        return "manageEmployees";
    }

    @RequestMapping(value = "/manageEnterprises", method = RequestMethod.GET)
    public String manageEnterprises() {
        return "manageEnterprises";
    }

    @RequestMapping(value = "/manageOffers", method = RequestMethod.GET)
    public String manageOffers(Model model) {
        List<EnterpriseProductEntity> enterpriseProductEntityList = productController.getProductsForEnterprise();
        model.addAttribute("products", enterpriseProductEntityList);
        return "manageOffers";
    }

    @RequestMapping(value = "/manageProducts", method = RequestMethod.GET)
    public String manageProducts(Model model) {
        List<EnterpriseProductEntity> enterpriseProductEntityList = productController.getProductsForEnterprise();
        model.addAttribute("products", enterpriseProductEntityList);
        return "manageProducts";
    }

    @RequestMapping(value = "/viewAccount", method = RequestMethod.GET)
    public String viewAccount() {

        return "viewAccount";
    }

    @RequestMapping(value = "/placeProducts", method = RequestMethod.GET)
    public String placeProducts(Model model) {
        List<EnterpriseProductEntity> enterpriseProductEntityList = productController.getProductsForEnterprise();
        model.addAttribute("products", enterpriseProductEntityList);
        return "placeProducts";
    }

}