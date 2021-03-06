package com.eci.cosw.project.quicklyshop.controller;

import com.eci.cosw.project.quicklyshop.model.Order;
import com.eci.cosw.project.quicklyshop.service.order.OrderService;
import com.eci.cosw.project.quicklyshop.service.order.exceptions.OrderServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class OrderController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable("id")String id) throws OrderServiceException {return orderService.getOrder(id);}

    @GetMapping("/orders")
    public List<Order> getOrders() throws OrderServiceException {return orderService.getAllOrders();}

    @PostMapping("/orders")
    public void addOrder(Order order ) throws OrderServiceException {orderService.addOrder(order);}

    @DeleteMapping("/oders")
    public void removeOrderById(String id) throws OrderServiceException {
        Order order = orderService.getOrder(id);
        orderService.removeOrder(order);
    }
    
}
