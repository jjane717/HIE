package org.yijun.hie.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.*;
import org.yijun.hie.service.CustomerService;
import org.yijun.hie.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ProductController productController;
    @Autowired
    private TestController testController;

    @RequestMapping(value = "/market", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<EnterpriseProductEntity> getProductEntityListForMarket(){
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<EnterpriseProductEntity> enterpriseProductEntityList;
        enterpriseProductEntityList = customerService.getProductEntityListForMarketFromService(customerService.getMarket(userAccountEntity));
        return enterpriseProductEntityList;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<OrderEntity> getOrderEntityListByUserAccount(){
        UserAccountEntity userAccountEntity = loginService.userLogin();
        List<OrderEntity> orderEntityList;
        orderEntityList = userAccountEntity.getOrderEntityList();
        return orderEntityList;
    }

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<PaymentEntity> getPaymentEntityListByOrder(Integer idOrder){
        idOrder = 1;
        List<PaymentEntity> paymentEntityList;
        paymentEntityList = customerService.getOrderEntityByIDFromService(idOrder).getPaymentEntityList();
        return paymentEntityList;
    }

    @RequestMapping(value = "/chooseProduct", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void chooseProduct(HttpServletRequest request){
        EnterpriseProductEntity enterpriseProductEntity = productController.getEnterpriseProductEntityById(Integer.valueOf(request.getParameter("id")));
        HttpSession session = request.getSession();
        synchronized (session){
            session.setAttribute("tempEnterpriseProduct", enterpriseProductEntity);
        }
    }

    @RequestMapping(value = "/chooseProduct", method = RequestMethod.GET)
    @Transactional
    public String chooseHIE (Model model, HttpServletRequest request) throws ServletException{
        HttpSession session = request.getSession();
        EnterpriseProductEntity enterpriseProductEntity = new EnterpriseProductEntity();
        synchronized (session) {
           enterpriseProductEntity = (EnterpriseProductEntity) session.getAttribute("tempEnterpriseProduct");
        }
        model.addAttribute("choose",enterpriseProductEntity);
        return "placeOrder";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void placeProduct(OrderEntity orderEntity, HttpServletRequest request) {
        HttpSession session = request.getSession();
        synchronized (session) {
            EnterpriseProductEntity enterpriseProductEntity = (EnterpriseProductEntity) session.getAttribute("tempEnterpriseProduct");
            orderEntity.setEnterpriseProductEntity(enterpriseProductEntity);
            orderEntity.setUserAccountEntity(testController.login());
            customerService.createOrderEntityByIDFromService(orderEntity);
        }
    }
}
