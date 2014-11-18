package org.yijun.hie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yijun.hie.persistence.entity.PaymentEntity;
import org.yijun.hie.persistence.repository.PaymentRepository;

/**
 * Created by liuyijun on 14-11-17.
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public void createPaymentFromService(PaymentEntity paymentEntity){
        paymentRepository.createPaymentFromRepository(paymentEntity);
    }

}
