package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.OrderEntity;
import org.yijun.hie.persistence.entity.ProductEntity;
import org.yijun.hie.persistence.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

    public List<OrderEntity> viewOrdersFromService(String name, Integer id){
        return orderRepository.viewOrdersFromRepository(name, id);
    }

    public List<OrderEntity> viewOrdersInsuranceFromService(String name){
        return orderRepository.viewOrdersInsuranceFromRepository(name);
    }

}
