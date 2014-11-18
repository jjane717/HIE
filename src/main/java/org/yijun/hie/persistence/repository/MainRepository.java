package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.OrderEntity;
import org.yijun.hie.persistence.entity.ProductEntity;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class MainRepository {
    @Autowired
    private SessionFactory sessionFactory;
    private String getProductByIDHql = "from ProductEntity where idProduct = :idProduct";
    private String getOrderByIDHql = "from OrderEntity where idOrder = :idOrder";

    public ProductEntity getProductByIDFromRepository(Integer idProduct){
        Session session = sessionFactory.getCurrentSession();
        ProductEntity productEntity = (ProductEntity)session.createQuery(getProductByIDHql).setInteger("idProduct",idProduct).list().get(0);
        return productEntity;
    }

    public OrderEntity getOrderByIDFromRepository(Integer idOrder){
        Session session = sessionFactory.getCurrentSession();
        OrderEntity orderEntity = (OrderEntity)session.createQuery(getOrderByIDHql).setInteger("idOrder",idOrder).list().get(0);
        return orderEntity;
    }
}
