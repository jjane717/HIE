package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.OrderEntity;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class OrderRepository{
    @Autowired
    private SessionFactory sessionFactory;

    private String getOrderByIDHql = "from OrderEntity where idOrder = :idOrder";

    public OrderEntity getOrderByIDFromRepository(Integer idOrder){
        Session session = sessionFactory.getCurrentSession();
        OrderEntity orderEntity = (OrderEntity)session.createQuery(getOrderByIDHql).setInteger("idOrder",idOrder).list().get(0);
        return orderEntity;
    }
}
