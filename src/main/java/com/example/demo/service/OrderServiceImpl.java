package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrders(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> getAllOrders(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    @Override
    public Order getOrder(String poNumber) {
        return orderRepository.findByPoNumber(poNumber);
    }

    @Override
    public Order getCartOrder(User user) {
        return orderRepository.findByUserAndOrderStatus(user, OrderStatus.CART);
    }

    @Override
    public Order getCartOrder(User user, boolean create) {
        Order order = getCartOrder(user);
        if(create && order == null){
            order = createOrder(user);
        }
        return order;
    }

    @Override
    public boolean hasCartOrder(User user) {
        return getCartOrder(user) != null ? true : false;
    }

    @Override
    @Transactional
    public Order createOrder(User user) {
        String poNumber = "OD-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMM-ddHH-mmss"));
        Order order = new Order(poNumber, user);
        orderRepository.saveAndFlush(order);
        entityManager.refresh(order);
        return order;
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void removeOrder(Order order) {

    }

    @Override
    public OrderItem getItem(Order order, Product product) {
        for (OrderItem item : order.getOrderItems()) {
            if(item.getProduct().getId() == product.getId()){
                return item;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Order addItem(User user, Product product, int quantity) {
        Order order = getCartOrder(user, true);
        OrderItem item = getItem(order, product);

        if(item != null){
            item.setQuantity(item.getQuantity() + quantity);
        }else{
            item = new OrderItem(order, product, quantity);
        }
        orderItemRepository.saveAndFlush(item);
        entityManager.refresh(order);
        return order;
    }

    @Override
    @Transactional
    public Order updateItem(User user, Product product, int quantity) {
        Order order = getCartOrder(user, true);
        OrderItem item = getItem(order, product);

        if(item != null){
            item.setQuantity(quantity);
        }else{
            item = new OrderItem(order, product, quantity);
        }
        orderItemRepository.saveAndFlush(item);
        entityManager.refresh(order);
        return order;
    }

    @Override
    @Transactional
    public Order removeItem(User user, Product product) {
        Order order = getCartOrder(user, true);
        OrderItem item = getItem(order, product);

        if(item != null){
            orderItemRepository.delete(item);
        }else{
            // throw new ProductNotFoundException();
        }
        entityManager.refresh(order);
        return order;
    }
}
