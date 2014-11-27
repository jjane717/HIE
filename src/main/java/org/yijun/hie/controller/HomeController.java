package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.UserRolePrivilegeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private UserRolePrivilegeService userRolePrivilegeService;
    @Autowired
    private CustomerController customerController;
    @Autowired
    private EnterpriseController enterpriseController;
    @Autowired
    private OrderController orderController;
    @Autowired
    private UserRolePrivilegeController userRolePrivilegeController;
    @Autowired
    private ManageController manageController;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest request){
        HttpSession session = request.getSession();
        modelAndView = new ModelAndView("login10");
        if(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")!=null){
            modelAndView.addObject("error", "Invalid username or password.");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    @Transactional
    public String userLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserAccountEntity userAccountEntity = loginService.userLogin();
        session.setAttribute("user", userAccountEntity);
        return "header";
    }

    @RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String update(HttpServletRequest request) {
        UserAccountEntity userAccountEntity = loginService.userLogin();
        loginService.updateUserAccount(userAccountEntity, request);
        return "OK";
    }

    @RequestMapping(value = "/system", method = RequestMethod.GET)
    @Transactional
    public String managementSystem(Model model) {
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<PrivilegeEntity> privilegeEntityList= userRolePrivilegeService.getParticularPrivileges(userAccountEntity);
        model.addAttribute("privileges", privilegeEntityList);
        return "manage";
    }

    @RequestMapping(value = "/hiUser", method = RequestMethod.GET)
    @Transactional
    public String hiUser(Model model) {
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<PrivilegeEntity> privilegeEntityList= userRolePrivilegeService.getParticularPrivileges(userAccountEntity);
        model.addAttribute("privileges", privilegeEntityList);
        return "hiUser";
    }

    @RequestMapping(value = "/manageUserAccounts", method = RequestMethod.GET)
    public String manageUserAccounts(Model model) {
        List<UserAccountEntity> userAccountEntityList = manageController.getUserAccountsByEnterprise();
        List<EnterpriseEntity> enterpriseEntityList = manageController.getEnterpriseList();
        model.addAttribute("enterprises",enterpriseEntityList);
        model.addAttribute("userAccounts", userAccountEntityList);
        return "manageUserAccounts";
    }

    @RequestMapping(value = "/manageEmployees", method = RequestMethod.GET)
    @Transactional
    public String manageEmployees(Model model) {
        List<UserAccountEntity> userAccountEntityList = userRolePrivilegeController.getAllEditableUserRole();
        model.addAttribute("userRoles", userAccountEntityList);
        UserAccountEntity userAccount = loginService.userLogin();
        model.addAttribute("available",userAccount.getRoleEntity().getPrivilegeEntityList());
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

    @RequestMapping(value = "/userHome", method = RequestMethod.GET)
    @Transactional
    public String userHome(Model model) {
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<OrderEntity> orderEntityList = userAccountEntity.getOrderEntityList();
        model.addAttribute("orders", orderEntityList);
        return "userHome";
    }

    @RequestMapping(value = "/viewMarket", method = RequestMethod.GET)
    public String viewMarket(Model model) {
        List<EnterpriseProductEntity> enterpriseProductEntityList = customerController.getProductEntityListForMarket();
        List<EnterpriseEntity> enterpriseEntityList = enterpriseController.getEnterpriseListForType("Insurance");
        model.addAttribute("products",enterpriseProductEntityList);
        model.addAttribute("enterprises", enterpriseEntityList);
        return "viewMarket";
    }

    @RequestMapping(value = "/makePayment", method = RequestMethod.GET)
    @Transactional
    public String chooseOneOrder(Model model) {
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<OrderEntity> orderEntityList = userAccountEntity.getOrderEntityList();
        model.addAttribute("orders", orderEntityList);
        return "makePayment";
    }

    @RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
    @Transactional
    public String orderHistory(Model model) {
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<OrderEntity> orderEntityList = userAccountEntity.getOrderEntityList();
        model.addAttribute("orders", orderEntityList);
        return "orderHistory";
    }

    @RequestMapping(value = "/choosePayment", method = RequestMethod.GET)
    public String chooseOnePayment(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        synchronized (session){
            OrderEntity orderEntity = (OrderEntity) session.getAttribute("selectOrder");
            List<PaymentEntity> paymentEntityList = orderEntity.getPaymentEntityList();
            model.addAttribute("payments", paymentEntityList);
        }
        return "choosePayment";
    }
}