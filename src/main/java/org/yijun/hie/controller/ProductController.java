package org.yijun.hie.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
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
    private EnterpriseController enterpriseController;
    @Autowired
    private ManageController manageController;

    @RequestMapping(value="/products", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public List<EnterpriseProductEntity> getProductsForEnterprise(){
        UserAccountEntity userAccountEntity = loginService.userLogin();
//        EnterpriseEntity enterpriseEntity = userAccountEntity.getEnterpriseEntity();
//        Hibernate.initialize(enterpriseEntity.getProductEntityList());
//        return enterpriseEntity.getProductEntityList();
        return productService.getProductEntityForEnterpriseFromService(userAccountEntity.getEnterpriseEntity().getIdEnterprise());
    }

    @RequestMapping(value="/findInsuranceEnterpriseForProduct", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public EnterpriseEntity findInsuranceEnterpriseForProduct(Integer idProduct){
        return productService.findInsuranceForProductsFromService(idProduct);
        //handle balance
    }

    @RequestMapping(value = "/updateProductStatus", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void updateProductStatus(HttpServletRequest request){
        Integer idEnterpriseProduct = Integer.valueOf(request.getParameter("id"));
        Boolean status = Boolean.valueOf(request.getParameter("status"));
        productService.updateProductStatusFromService(idEnterpriseProduct, status);
    }

    @RequestMapping(value = "/updateProductPrice", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void updateProductPrice(HttpServletRequest request){
        Integer idEnterpriseProduct = Integer.valueOf(request.getParameter("id"));
        Double totalPrice = Double.valueOf(request.getParameter("totalPrice"));
        productService.updateProductPriceFromService(idEnterpriseProduct, totalPrice);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ProductEntity getProductEntityByID(HttpServletRequest request){
        return productService.getProductEntityByIDFromService(Integer.valueOf(request.getParameter("id")));
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ProductEntity updateProductEntity(ProductEntity productEntity){
        return productService.createProductEntityFromService(productEntity);
    }

    @RequestMapping(value = "/chooseOffer", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void chooseOffer(HttpServletRequest request){
        ProductEntity productEntity = getProductEntityByID(request);
        HttpSession session = request.getSession();
        synchronized (session){
        session.setAttribute("tempProduct", productEntity);
        }
    }

    @RequestMapping(value = "/chooseOffer", method = RequestMethod.GET)
    @Transactional
    public String chooseHIE (Model model, HttpServletRequest request){
        List<EnterpriseEntity> enterpriseEntityList= enterpriseController.getEnterpriseListForType("HIE");
        List<EnterpriseEntity> hieList= new LinkedList<EnterpriseEntity>();
        HttpSession session = request.getSession();
        synchronized (session) {
            ProductEntity productEntity = (ProductEntity) session.getAttribute("tempProduct");
            hieList = productService.getHIEEnterpriseListByIdProductFromService(enterpriseEntityList,productEntity.getIdProduct(),false);
        }
        model.addAttribute("hies",hieList);
        return "chooseHIEEnterprise";
    }

    @RequestMapping(value = "/placeProducts", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void placeProduct(HttpServletRequest request) {
        EnterpriseEntity enterpriseEntity = manageController.getEnterpriseById(Integer.valueOf(request.getParameter("id")));
        HttpSession session = request.getSession();
        synchronized (session) {
            ProductEntity productEntity = (ProductEntity) session.getAttribute("tempProduct");
            productService.placeProductFromService(enterpriseEntity,productEntity);
        }
    }

    @Transactional
    public EnterpriseProductEntity getEnterpriseProductEntityById(Integer idEnterpriseProduct){
        return productService.getEnterpriseProductEntityByIdFromService(idEnterpriseProduct);
    }
}
