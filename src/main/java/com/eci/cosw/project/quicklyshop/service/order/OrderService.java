package com.eci.cosw.project.quicklyshop.service.order;

import com.eci.cosw.project.quicklyshop.model.Order;
import com.eci.cosw.project.quicklyshop.service.inventory.exceptions.InventoryServiceException;
import com.eci.cosw.project.quicklyshop.service.order.exceptions.OrderServiceException;

import java.util.List;

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

    /**
     * Consulta todas las ordenes en el sistemas
     * @return lista de tipo Order con las caracteristicas de cada una de las ordenes
     * @throws OrderServiceException cuando no existen ordenes registradas
     */
    List<Order> getAllOrders() throws  OrderServiceException;


    /**
     * Remueve una orden dado su identificador
     * @param id de la orden que se desea remover
     * @throws OrderServiceException cuando la orden con el identificador dado no existe
     */
    void removeOrderById(String id) throws OrderServiceException;
}
