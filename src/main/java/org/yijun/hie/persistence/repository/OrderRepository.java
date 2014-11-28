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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liuyijun on 14-11-17.
 */
@Repository
public class OrderRepository{
    @Autowired
    private SessionFactory sessionFactory;

    private String getOrderByIDHql = "from OrderEntity where idOrder = :idOrder";
    private String getPaymentByIDHql = "from PaymentEntity where idPayment = :idPayment";
    private String getOrder = "from OrderEntity";

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

    public List<OrderEntity> viewOrdersFromRepository(String enterpriseName, Integer idEnterprise){
        Session session = sessionFactory.getCurrentSession();
        List<OrderEntity> orderEntityList = session.createQuery(getOrder).list();
        List<OrderEntity> orderEntities = new LinkedList<OrderEntity>();
        for(OrderEntity order:orderEntityList){
            if(order.getEnterpriseProductEntity().getEnterpriseEntity().getIdEnterprise().equals(idEnterprise)){
                if(order.getEnterpriseProductEntity().getProductEntity().getInsuranceEnterpriseName().equals(enterpriseName)){
                    orderEntities.add(order);
                }
            }
        }
        return orderEntities;
    }

    public List<OrderEntity> viewOrdersInsuranceFromRepository(String name){
        Session session = sessionFactory.getCurrentSession();
        List<OrderEntity> orderEntityList = session.createQuery(getOrder).list();
        List<OrderEntity> orderEntities = new LinkedList<OrderEntity>();
        for(OrderEntity order:orderEntityList){
            if(order.getEnterpriseProductEntity().getProductEntity().getInsuranceEnterpriseName().equals(name)){
                orderEntities.add(order);
            }
        }
        return orderEntities;
    }
}
