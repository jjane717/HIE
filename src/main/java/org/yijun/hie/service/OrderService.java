package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.OrderEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by liuyijun on 14-11-17.
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity getOrderByIDFromService(Integer idOrder){
        return orderRepository.getOrderByIDFromRepository(idOrder);
    }

    public void updatePaymentFromService(Integer idPayment, Integer idOrder){
        orderRepository.updatePaymentFromRepository(idPayment,idOrder);
    }

}
