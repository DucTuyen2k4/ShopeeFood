package com.example.shopeefood.service.cart;

import com.example.shopeefood.model.Cart;
import com.example.shopeefood.model.User;
import com.example.shopeefood.service.IGenerateService;

public interface ICartService extends IGenerateService<Cart> {
    Iterable<Cart> findCartByIdUser(User idUser);
}

