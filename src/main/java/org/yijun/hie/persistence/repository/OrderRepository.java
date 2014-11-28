package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.EnterpriseEntity;
import org.yijun.hie.persistence.entity.OrderEntity;
import org.yijun.hie.persistence.entity.PaymentEntity;

import java.time.Instant;
import java.util.Date;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class OrderRepository{
    @Autowired
    private SessionFactory sessionFactory;

    private String getOrderByIDHql = "from OrderEntity where idOrder = :idOrder";
    private String getPaymentByIDHql = "from PaymentEntity where idPayment = :idPayment";

    public OrderEntity getOrderByIDFromRepository(Integer idOrder){
        Session session = sessionFactory.getCurrentSession();
        OrderEntity orderEntity = (OrderEntity)session.createQuery(getOrderByIDHql).setInteger("idOrder",idOrder).list().get(0);
        return orderEntity;
    }

    public void updatePaymentFromRepository(Integer idPayment,Integer idOrder){
        Session session = sessionFactory.getCurrentSession();
        PaymentEntity paymentEntity = (PaymentEntity)session.createQuery(getPaymentByIDHql).setInteger("idPayment",idPayment).list().get(0);
        paymentEntity.setIsPay(true);
        Date date = Date.from(Instant.now());
        paymentEntity.setPayDate(date);
        EnterpriseEntity enterpriseEntity= getOrderByIDFromRepository(idOrder).getEnterpriseProductEntity().getEnterpriseEntity();
        enterpriseEntity.setEnterpriseBalance(enterpriseEntity.getEnterpriseBalance()+paymentEntity.getAmount());
        session.update(enterpriseEntity);
        session.update(paymentEntity);
        session.flush();
    }
}
