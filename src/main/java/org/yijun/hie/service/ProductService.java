package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.repository.ProductRepository;

import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductEntity getProductEntityByIDFromService(Integer idProduct){
        return productRepository.getProductByIDFromRepository(idProduct);
    }

    public void updateProductStatusFromService(Integer idProduct, Boolean status){
        ProductEntity productEntity = getProductEntityByIDFromService(idProduct);
        productEntity.setStatus(status);
        productRepository.updateProductStatusFromRepository(productEntity);
    }
}
