package org.yijun.hie.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.EnterpriseProductEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.entity.UserAccountEntity;
import org.yijun.hie.persistence.repository.ProductRepository;
import org.yijun.hie.service.LoginService;
import org.yijun.hie.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Controller
public class ProductController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value="/products", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<ProductEntity> getProductsForEnterprise(){
        UserAccountEntity userAccountEntity = loginService.userLogin("ddd", "ddddd");
        EnterpriseEntity enterpriseEntity = userAccountEntity.getEnterpriseEntity();
        Hibernate.initialize(enterpriseEntity.getProductEntityList());
        return enterpriseEntity.getProductEntityList();
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void updateProductStatus(HttpServletRequest request){
        Integer idProduct = Integer.valueOf(request.getParameter("id"));
        Boolean status = Boolean.valueOf(request.getParameter("status"));
        productService.updateProductStatusFromService(idProduct, status);
    }
}
