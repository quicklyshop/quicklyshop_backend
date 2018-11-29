package com.eci.cosw.project.quicklyshop.service.order;

import com.eci.cosw.project.quicklyshop.model.Order;
import com.eci.cosw.project.quicklyshop.service.order.exceptions.OrderServiceException;

public interface OrderService {
    /**
     * Añade nuevas ordenes de los usuarios que ya se han generado
     * @param order objeto de tipo "order" con la información de la orden generada
     * @throws OrderServiceException ya existe la orden
     */
    void addOrder (Order order) throws OrderServiceException;

    /**
     * Consulta una order dado su identificador
     * @param id identificador unico de la orden
     * @throws OrderServiceException si la orden no existe
     */
    Order getOrderById(String id) throws OrderServiceException;
}
