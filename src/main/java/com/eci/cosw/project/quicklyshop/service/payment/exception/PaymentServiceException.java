package com.eci.cosw.project.quicklyshop.service.payment.exception;

import com.eci.cosw.project.quicklyshop.model.Payment;

public class PaymentServiceException extends Exception{
    public PaymentServiceException() {  super(); }

    public PaymentServiceException(String msg) {
        super(msg);
    }
}


