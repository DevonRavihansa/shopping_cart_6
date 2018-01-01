package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @Getter
    @Setter
    private String poNumber;
    @Getter @Setter private UserDto user;
    @Getter @Setter private String status;
    @Getter @Setter private Date created;
    @Getter @Setter private List<OrderItemDto> items;
    private double total;

    public double getTotal() {
        total = 0.0;
        for (OrderItemDto item : items) {
            total += item.getSubTotal();
        }
        return total;
    }
}
