package com.eci.cosw.project.quicklyshop.service.payment.impl;

import com.eci.cosw.project.quicklyshop.model.Payment;
import com.eci.cosw.project.quicklyshop.service.payment.PaymentService;
import com.eci.cosw.project.quicklyshop.service.payment.exception.PaymentServiceException;
import com.eci.cosw.project.quicklyshop.service.payment.persistence.PaymentRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);
    
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) throws PaymentServiceException {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(String id) throws PaymentServiceException {
        return paymentRepository.findPaymentById(id);
    }

    @Override
    public List<Payment> getPayments() throws PaymentServiceException {
        return paymentRepository.findAllPayment();
    }
    
}
