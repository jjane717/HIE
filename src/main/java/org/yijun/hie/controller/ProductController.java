package org.yijun.hie.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value="/products", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<ProductEntity> getProductsForEnterprise(){
        UserAccountEntity userAccountEntity = loginService.userLogin("ddd", "ddddd");
        //List<EnterpriseProductEntity> enterpriseProductEntityList = productRepository.getProductsByEnterpriseId(2);
        List<EnterpriseEntity> enterpriseEntityList = productRepository.getEnterprise(2);
        Integer idEnterprise = userAccountEntity.getEnterpriseEntity().getIdEnterprise();
        EnterpriseEntity enterpriseEntity = enterpriseEntityList.get(0);
        Hibernate.initialize(enterpriseEntity.getProductEntityList());
        return enterpriseEntity.getProductEntityList();
        //return userAccountEntity.getEnterpriseEntity().getProductEntityList();
    }
}
