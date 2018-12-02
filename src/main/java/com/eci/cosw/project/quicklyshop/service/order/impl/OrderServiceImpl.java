package com.eci.cosw.project.quicklyshop.service.order.impl;

import com.eci.cosw.project.quicklyshop.model.Order;
import com.eci.cosw.project.quicklyshop.service.order.OrderService;
import com.eci.cosw.project.quicklyshop.service.order.exceptions.OrderServiceException;
import com.eci.cosw.project.quicklyshop.service.order.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) throws OrderServiceException {
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(String id) throws OrderServiceException {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() throws OrderServiceException {
        return orderRepository.findAll();
    }

    @Override
    public void removeOrder(Order order) throws OrderServiceException {
        orderRepository.delete(order);
    }
}
