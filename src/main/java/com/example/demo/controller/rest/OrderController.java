package com.example.demo.controller.rest;

import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@RestController
@RequestMapping("admin/rest/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<OrderDto> getOrders(){
        // setting UserDto mappings...
        return createOrderDtos(orderService.getAllOrders());
    }

    @RequestMapping("/{ponumber}")
    public OrderDto getOrder(@PathVariable("ponumber")String poNumber){
        return createDto(orderService.getOrder(poNumber));
    }

    private OrderDto createDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }

    private List<OrderDto> createOrderDtos(List<Order> orders){
        return modelMapper.map(orders, new TypeToken<List<OrderDto>>() {}.getType());
    }
}
