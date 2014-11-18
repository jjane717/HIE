package org.yijun.hie.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yijun.hie.persistence.entity.PaymentEntity;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class PaymentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void createPaymentFromRepository(PaymentEntity paymentEntity){
        Session session = sessionFactory.getCurrentSession();
        session.save(paymentEntity);
        session.flush();
    }
}
