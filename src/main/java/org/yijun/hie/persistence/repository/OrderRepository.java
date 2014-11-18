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
public class OrderRepository extends MainRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private OrderEntity createOrderEntityFromRepository(OrderEntity orderEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(orderEntity);
        session.flush();
        return orderEntity;
    }

}
