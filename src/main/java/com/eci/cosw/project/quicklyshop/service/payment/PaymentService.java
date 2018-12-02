package com.eci.cosw.project.quicklyshop.service.payment;

import com.eci.cosw.project.quicklyshop.model.Payment;
import com.eci.cosw.project.quicklyshop.service.payment.exception.PaymentServiceException;

import java.util.List;

public interface PaymentService {
    /**
     * Retorna los pagos que han sido registrados
     * @return Lista de tipo Payment con las caracteristicas de cada pago
     * @throws PaymentServiceException cuando no existen pagos registrados en la base de datos
     */
    Payment  getPayment(String id) throws PaymentServiceException;

    /**
     * Añade nuevos pagos a la base de datos
     * @param payment objeto Payment con infomración acerca del pago
     * @return Payment objeto con información del pago
     * @throws PaymentServiceException cuando el objeto payment es nulo
     */
    Payment createPayment(Payment payment)throws PaymentServiceException;



    /**
     * Retorna un pago dado su identificador
     * @param id de la transacción
     * @return Payment objeto con la infomación del pago
     * @throws PaymentServiceException cuando no existe el pago con el identificador
     */
    Payment getPaymentById(String id) throws  PaymentServiceException;


    List<Payment> getPayments() throws  PaymentServiceException;

}
