package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Devon Ravihansa on 10/9/2017.
 */
@Controller
public class DefaultController {

    public static final String VIEW_NAME = "view";
    public static final String DEFAULT_LAYOUT = "layout/default";

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute(VIEW_NAME, "index");

        return DEFAULT_LAYOUT;
    }

    @RequestMapping("/shop")
    public String shop(Model model){
        model.addAttribute(VIEW_NAME, "shop");

        model.addAttribute("products", createDtos(productService.getAllProducts()));
        return DEFAULT_LAYOUT;
    }

    @RequestMapping("/product/{sku}")
    public String product(@PathVariable("sku")String sku, Model model){
        model.addAttribute(VIEW_NAME, "product");

        model.addAttribute("product", createDto(productService.getProduct(sku)));
        return DEFAULT_LAYOUT;
    }

    @RequestMapping("/cart")
    public String cart(Model model){
        model.addAttribute(VIEW_NAME, "cart");

        return DEFAULT_LAYOUT;
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute(VIEW_NAME, "login");

        return DEFAULT_LAYOUT;
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute(VIEW_NAME, "signup");

        return DEFAULT_LAYOUT;
    }

    @RequestMapping("/user/{username}")
    public String user(Model model){
        model.addAttribute(VIEW_NAME, "user");

        return DEFAULT_LAYOUT;
    }

    private ProductDto createDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }

    private List<ProductDto> createDtos(List<Product> products){
        return modelMapper.map(products, new TypeToken<List<ProductDto>>() {}.getType());
    }
}
