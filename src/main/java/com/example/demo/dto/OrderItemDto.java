package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    @Getter
    @Setter
    private ProductDto product;
    @Getter @Setter private int quantity;
    private double subTotal;

    public double getSubTotal() {
        subTotal = product.getPrice() * quantity;
        return subTotal;
    }
}
