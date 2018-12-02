package com.eci.cosw.project.quicklyshop.service.order.persistence;

import com.eci.cosw.project.quicklyshop.model.Order;
import com.eci.cosw.project.quicklyshop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findOrderByUser(User user);
    Order findOrderById(String id);
    List<Order> findAll();
    List<Order> getAllOrders();
}
