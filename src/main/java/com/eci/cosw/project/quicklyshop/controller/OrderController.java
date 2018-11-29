package com.eci.cosw.project.quicklyshop.controller;

import com.eci.cosw.project.quicklyshop.model.Order;
import com.eci.cosw.project.quicklyshop.service.order.persistence.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class OrderController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<Order> getOrders(){return orderRepository}

}
