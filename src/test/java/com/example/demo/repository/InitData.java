package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitData {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void addProducts(){
        // p
        Product p = new Product("MP-001", "Milk Powder", 295.00);
        p.setImage("/images/img_1.jpg");
        productRepository.saveAndFlush(p);
        // p2
        Product p2 = new Product("YH-001", "Yoghurt", 35.00);
        p2.setImage("/images/img_2.jpg");
        productRepository.saveAndFlush(p2);
        // p3
        Product p3 = new Product("CD-001", "Curd", 150.00);
        p3.setImage("/images/img_3.jpg");
        productRepository.saveAndFlush(p3);
        // p4
        Product p4 = new Product("BT-001", "Butter", 380.00);
        p4.setImage("/images/img_4.jpg");
        productRepository.saveAndFlush(p4);
        // p5
        Product p5 = new Product("CH-001", "Cheese", 250.00);
        p5.setImage("/images/img_5.jpg");
        productRepository.saveAndFlush(p5);
        // p6
        Product p6 = new Product("IC-001", "Ice Cream", 250.00);
        p6.setImage("/images/img_6.jpg");
        productRepository.saveAndFlush(p6);
        // p7
        Product p7 = new Product("ML-001", "Milk", 60.00);
        p7.setImage("/images/img_7.jpg");
        productRepository.saveAndFlush(p7);
    }

    public void addUsers(){
        // u
        User u = new User("user1", "123");
        u.setEmail("user1@example.com");
        userRepository.saveAndFlush(u);
        // u2
        User u2 = new User("user2", "456");
        u2.setEmail("user2@example.com");
        userRepository.saveAndFlush(u2);
        // u3
        User u3 = new User("admin1", "123");
        u3.setEmail("admin1@example.com");
        userRepository.saveAndFlush(u3);
        // u4
        User u4 = new User("admin2", "456");
        u4.setEmail("admin2@example.com");
        userRepository.saveAndFlush(u4);

        // u5
        User u5 = new User("user3", "789");
        u5.setEmail("user3@example.com");
        userRepository.saveAndFlush(u5);
        // u6
        User u6 = new User("admin3", "789");
        u6.setEmail("admin3@example.com");
        u6.setEnabled(false);
        userRepository.saveAndFlush(u6);
    }

    public void addUserRoles(){
        // ur
        UserRole ur = new UserRole("ROLE_ADMIN");
        userRoleRepository.saveAndFlush(ur);
        // ur2
        UserRole ur2 = new UserRole("ROLE_USER");
        userRoleRepository.saveAndFlush(ur2);
    }

    public void getUser(){
        User user3 = userRepository.findOne(Long.valueOf(10));
        User admin3 = userRepository.findOne(Long.valueOf(11));

        System.err.println("user "+user3.isEnabled());
        System.err.println("admin " + admin3.isEnabled());
    }

    public void orderDto(){
        Order order = orderRepository.findByPoNumber("OD-1710-0218-0943");
        System.err.println(order.getOrderStatus().name());
        OrderDto dto = modelMapper.map(order, OrderDto.class);
        System.err.println(dto.getUser().getUsername());
    }
}
