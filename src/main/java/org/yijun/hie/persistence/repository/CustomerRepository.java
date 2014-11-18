package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.EnterpriseProductEntity;
import org.yijun.hie.persistence.entity.OrderEntity;
import org.yijun.hie.persistence.entity.ProductEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class CustomerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private String getProductEntityListForMarketHql = "from EnterpriseProductEntity where targetMarket = :market";
    private String getProductEntityByIDHql = "from ProductEntity where idProduct = :idProduct";
    private String getOrderEntityByIDHql = "from OrderEntity where idOrder = :idOrder";

    public List<EnterpriseProductEntity> getProductEntityListForMarketFormRepository(String market){
        Session session = sessionFactory.getCurrentSession();
        List<EnterpriseProductEntity> enterpriseProductEntityList;
        enterpriseProductEntityList = session.createQuery(getProductEntityListForMarketHql).setString("market",market).list();
        List<EnterpriseProductEntity> hieEnterpriseProductEntityList = new LinkedList<EnterpriseProductEntity>();
        for(EnterpriseProductEntity enterpriseProductEntity:enterpriseProductEntityList){
            if(enterpriseProductEntity.getEnterpriseEntity().getEnterpriseType().equals("HIE")){
                hieEnterpriseProductEntityList.add(enterpriseProductEntity);
            }
        }
        return hieEnterpriseProductEntityList;
    }

    public ProductEntity getProductEntityByIDFromRepository (Integer idProduct){
        Session session = sessionFactory.getCurrentSession();
        ProductEntity productEntity;
        productEntity = (ProductEntity) session.createQuery(getProductEntityByIDHql).setInteger("idProduct",idProduct).list().get(0);
        return productEntity;
    }

    public OrderEntity getOrderEntityByIDFromRepository (Integer idOrder){
        Session session = sessionFactory.getCurrentSession();
        OrderEntity orderEntity;
        orderEntity = (OrderEntity) session.createQuery(getOrderEntityByIDHql).setInteger("idOrder",idOrder).list().get(0);
        return orderEntity;
    }


}
