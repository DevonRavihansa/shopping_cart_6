package com.example.demo.service.cart;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.cart.Cart;

import javax.servlet.http.HttpSession;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
public interface CartService {
    Cart getCart(HttpSession session);
    Cart getCart(HttpSession session, boolean create);
    void setCart(HttpSession session, Cart cart);
    void removeCart(HttpSession session);
    boolean hasCart(HttpSession session);

    Cart addItem(HttpSession session, ProductDto product, int quantity);
    Cart updateItem(HttpSession session, ProductDto product, int quantity);
    Cart removeItem(HttpSession session, ProductDto product);
}
