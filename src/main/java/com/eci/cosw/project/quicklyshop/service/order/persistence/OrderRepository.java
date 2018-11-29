package com.eci.cosw.project.quicklyshop.service.order.persistence;

import com.eci.cosw.project.quicklyshop.model.Order;
import com.eci.cosw.project.quicklyshop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findOrderByUser(User user);
    Order findOrderById(String id);
}
