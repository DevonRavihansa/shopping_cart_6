package com.example.demo.controller.rest;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.cart.Cart;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.service.cart.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@RestController
@RequestMapping("/rest/cart")
public class CartOrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Cart getCart(HttpSession session, Authentication authentication){
        if(authentication != null){
            User user = userService.getUser(authentication.getName());
            return orderService.hasCartOrder(user) ? createCart(orderService.getCartOrder(user)) : new Cart(); // if not return an empty cart (dummy)
        }
        return cartService.hasCart(session) ? cartService.getCart(session) : new Cart(); // if not return an empty cart (dummy)
    }

    @PostMapping
    public Cart addItem(HttpSession session, Authentication authentication, @RequestParam("sku")String sku,
                        @RequestParam(value = "quantity", required = false, defaultValue = "1")int quantity){
        Product product = productService.getProduct(sku);
        if(authentication != null){
            return createCart(orderService.addItem(userService.getUser(authentication.getName()), product, quantity));
        }
        return cartService.addItem(session, createDto(product), quantity);
    }

    @PutMapping
    public Cart updateItem(HttpSession session, Authentication authentication, @RequestParam("sku")String sku,
                           @RequestParam("quantity")int quantity){
        Product product = productService.getProduct(sku);
        if(authentication != null){
            return createCart(orderService.updateItem(userService.getUser(authentication.getName()), product, quantity));
        }
        return cartService.updateItem(session, createDto(product), quantity);
    }

    @DeleteMapping
    public Cart removeItem(HttpSession session, Authentication authentication, @RequestParam("sku")String sku){
        Product product = productService.getProduct(sku);
        if(authentication != null){
            return createCart(orderService.removeItem(userService.getUser(authentication.getName()), product));
        }
        return cartService.removeItem(session, createDto(product));
    }

    private Cart createCart(Order order){
        Cart cart = new Cart();
        for (OrderItem item : order.getOrderItems()) {
            cart.addItem(createDto(item.getProduct()), item.getQuantity());
        }
        return cart;
    }

    private ProductDto createDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}
