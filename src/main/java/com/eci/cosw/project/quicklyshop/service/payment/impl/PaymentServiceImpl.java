package com.eci.cosw.project.quicklyshop.service.payment.impl;

import com.eci.cosw.project.quicklyshop.model.Payment;
import com.eci.cosw.project.quicklyshop.service.payment.PaymentService;
import com.eci.cosw.project.quicklyshop.service.payment.exception.PaymentServiceException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);
    
    

    @Override
    public Payment getPayment(String id) throws PaymentServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Payment createPayment(Payment payment) throws PaymentServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Payment getPaymentById(String id) throws PaymentServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Payment> getPayments() throws PaymentServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
