package com.eci.cosw.project.quicklyshop.controller;


import com.eci.cosw.project.quicklyshop.model.Payment;
import com.eci.cosw.project.quicklyshop.service.payment.PaymentService;
import com.eci.cosw.project.quicklyshop.service.payment.exception.PaymentServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class PaymentController {

    private static final Logger logger = LogManager.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @GetMapping(path="/payment")
    public List<Payment> getPayments() throws PaymentServiceException {
        return paymentService.getPayments();
    }

    @PostMapping(path = "/payment")
    public ResponseEntity<?> addPayment(Payment payment) throws PaymentServiceException{
        paymentService.createPayment(payment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/payment/{id}")
    public Payment getPaymentById(@PathVariable String id ) throws PaymentServiceException{
        return paymentService.getPaymentById(id);
    }


}
