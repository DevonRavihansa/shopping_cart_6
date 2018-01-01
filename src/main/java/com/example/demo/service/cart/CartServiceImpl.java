package com.example.demo.service.cart;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.cart.Cart;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@Service
public class CartServiceImpl implements CartService {

    public static final String SESSION_KEY_CART = "cart";

    @Override
    public Cart getCart(HttpSession session) {
        return (Cart) session.getAttribute(SESSION_KEY_CART);
    }

    @Override
    public Cart getCart(HttpSession session, boolean create) {
        Cart cart = getCart(session);

        if(create && cart == null){
            cart = new Cart();
            setCart(session, cart);
        }
        return cart;
    }

    @Override
    public void setCart(HttpSession session, Cart cart) {
        session.setAttribute(SESSION_KEY_CART, cart);
    }

    @Override
    public void removeCart(HttpSession session) {
        session.removeAttribute(SESSION_KEY_CART);
    }

    @Override
    public boolean hasCart(HttpSession session) {
        return getCart(session) != null ? true : false;
    }

    @Override
    public Cart addItem(HttpSession session, ProductDto product, int quantity) {
        return getCart(session, true).addItem(product, quantity);
    }

    @Override
    public Cart updateItem(HttpSession session, ProductDto product, int quantity) {
        return getCart(session, true).updateItem(product, quantity);
    }

    @Override
    public Cart removeItem(HttpSession session, ProductDto product) {
        return getCart(session, true).removeItem(product);
    }
}
