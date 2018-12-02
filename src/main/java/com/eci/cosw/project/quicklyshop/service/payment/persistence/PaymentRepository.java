package com.eci.cosw.project.quicklyshop.service.payment.persistence;

import com.eci.cosw.project.quicklyshop.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {

        Payment save(Payment payment);

        void removePayment(Payment payment);

        Payment findPaymentById(String s) ;

        List<Payment> findAllPayment();

}
