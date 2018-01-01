package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByPoNumber(String poNumber);
    List<Order> findByUser(User user);
    List<Order> findByOrderStatus(OrderStatus orderStatus);

    Order findByUserAndOrderStatus(User user, OrderStatus orderStatus);
}
