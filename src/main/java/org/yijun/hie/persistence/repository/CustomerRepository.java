package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.ProductEntity;

import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class CustomerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private String getProductListForMarketHql = "from ProductEntity where targetMarket = :market";

    public List<ProductEntity> getProductEntityListForMarketFormRepository(String market){
        Session session = sessionFactory.getCurrentSession();
        List<ProductEntity> productEntityList;
        productEntityList = session.createQuery(getProductListForMarketHql).setString("market",market).list();
        return productEntityList;
    }
}
