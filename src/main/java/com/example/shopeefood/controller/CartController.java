package com.example.shopeefood.controller;

import com.example.shopeefood.model.Cart;
import com.example.shopeefood.model.User;
import com.example.shopeefood.service.cart.ICartService;
import com.example.shopeefood.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICartService iCartService;
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> cartList = (List<Cart>) iCartService.findAll();
        if (cartList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Cart>> findCartByIdUser(@PathVariable long idUser) {
        Optional<User> user = iUserService.findById(idUser);
        List<Cart> cartList = (List<Cart>) iCartService.findCartByIdUser(user.get());
        if (cartList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(cartList, HttpStatus.OK);
        }
    }
}