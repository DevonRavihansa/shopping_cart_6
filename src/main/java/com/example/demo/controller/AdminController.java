package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "admin/dashboard";
    }

    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }

    @RequestMapping("/view/orders")
    public String orders(){
        return "admin/view/order::all";
    }

    @RequestMapping("/view/orders/detail")
    public String ordersDetail(){
        return "admin/view/order::detail";
    }

    @RequestMapping("/view/products")
    public String products(){
        return "admin/view/product::all";
    }

    @RequestMapping("/view/products/detail")
    public String productsDetail(){
        return "admin/view/product::detail";
    }

    @RequestMapping("/view/products/create")
    public String productsCreate(){
        return "admin/view/product::create";
    }

    @RequestMapping("/view/users")
    public String users(){
        return "admin/view/user::all";
    }

    @RequestMapping("/view/users/detail")
    public String usersDetail(){
        return "admin/view/user::detail";
    }

    @RequestMapping("/view/users/create")
    public String usersCreate(){
        return "admin/view/user::create";
    }

    @RequestMapping("/view/overview")
    public String overview(){
        return "admin/view/overview::content";
    }
}
