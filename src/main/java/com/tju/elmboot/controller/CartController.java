package com.tju.elmboot.controller;

import com.tju.elmboot.po.Cart;
import com.tju.elmboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CartController")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/listCart")
    public List<Cart> listCart(Cart cart) {
        return cartService.listCart(cart);
    }

    @RequestMapping("/saveCart")
    public int saveCart(Cart cart) {
        return cartService.saveCart(cart);
    }

    @RequestMapping("/updateCart")
    public int updateCart(Cart cart) {
        return cartService.updateCart(cart);
    }

    @RequestMapping("/removeCart")
    public int removeCart(Cart cart) {
        return cartService.removeCart(cart);
    }
}
