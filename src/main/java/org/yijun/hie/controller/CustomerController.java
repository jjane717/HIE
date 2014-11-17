package org.yijun.hie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.service.CustomerService;
import org.yijun.hie.service.LoginService;

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

    @RequestMapping(value = "/market", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<ProductEntity> getProductEntityListForMarket(){
        UserAccountEntity userAccountEntity = loginService.userLogin("ccc", "ccccc");
        List<ProductEntity> productEntityList;
        productEntityList = customerService.getProductEntityListForMarketFromService(customerService.getMarket(userAccountEntity));
        return productEntityList;
    }


}
