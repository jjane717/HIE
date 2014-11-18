package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.repository.OrderRepository;

/**
 * Created by liuyijun on 14-11-17.
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
