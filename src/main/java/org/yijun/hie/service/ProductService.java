package org.yijun.hie.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.EnterpriseProductEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.repository.ProductRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LoginService loginService;

    public ProductEntity getProductEntityByIDFromService(Integer idProduct){
        return productRepository.getProductByIDFromRepository(idProduct);
    }

    public void updateProductStatusFromService(Integer idEnterpriseProduct, Boolean status){
        EnterpriseProductEntity enterpriseProductEntity = productRepository.getEnterpriseProductEntityByID(idEnterpriseProduct);
        enterpriseProductEntity.setStatus(status);
        productRepository.updateEnterpriseProductStatusFromRepository(enterpriseProductEntity);
    }

    public void updateProductPriceFromService(Integer idEnterpriseProduct, Double totalPrice){
        EnterpriseProductEntity enterpriseProductEntity = productRepository.getEnterpriseProductEntityByID(idEnterpriseProduct);
        enterpriseProductEntity.setTotalPrice(totalPrice);
        productRepository.updateEnterpriseProductStatusFromRepository(enterpriseProductEntity);
    }

    public EnterpriseEntity findInsuranceForProductsFromService(Integer idProduct){
        return productRepository.findInsuranceForProductFromRepository(getProductEntityByIDFromService(idProduct));
    }

    public List<EnterpriseProductEntity> getProductEntityForEnterpriseFromService(Integer idEnterprise){
        return productRepository.getProductEntityForEnterpriseFromRepository(idEnterprise);
    }

    public ProductEntity createProductEntityFromService(ProductEntity productEntity){
        EnterpriseEntity enterpriseEntity = loginService.userLogin().getEnterpriseEntity();
        List<EnterpriseEntity> enterpriseEntityList = new LinkedList<EnterpriseEntity>();
        enterpriseEntityList.add(enterpriseEntity);
        productEntity.setEnterpriseEntities(enterpriseEntityList);
        productEntity.setInsuranceEnterpriseName(enterpriseEntity.getEnterpriseName());
        return productRepository.createProductEntityFromRepository(productEntity);
    }

    public void placeProductFromService(EnterpriseEntity enterpriseEntity, ProductEntity productEntity){
        EnterpriseProductEntity enterpriseProductEntity = new EnterpriseProductEntity();
        enterpriseProductEntity.setIdEnterprise(enterpriseEntity.getIdEnterprise());
        enterpriseProductEntity.setIdProduct(productEntity.getIdProduct());
        enterpriseProductEntity.setEnterpriseEntity(enterpriseEntity);
        enterpriseProductEntity.setProductEntity(productEntity);
        enterpriseProductEntity.setTargetMarket(productEntity.getTargetMarket());
        enterpriseProductEntity.setTotalPrice(productEntity.getOfferPrice());
        enterpriseProductEntity.setStatus(false);
        productRepository.placeProductFromRepository(enterpriseProductEntity);
    }

    public List<EnterpriseEntity> getHIEEnterpriseListByIdProductFromService(List<EnterpriseEntity> hieEnterpriseList, Integer idProduct, Boolean isHIE){
        return productRepository.getHIEEnterpriseListByIdProductFromRepository(hieEnterpriseList,idProduct, isHIE);
    }
}
