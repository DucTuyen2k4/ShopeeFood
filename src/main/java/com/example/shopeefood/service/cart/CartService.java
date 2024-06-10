package com.example.shopeefood.service.cart;
import com.example.shopeefood.model.Cart;
import com.example.shopeefood.model.User;
import com.example.shopeefood.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CartService implements ICartService{
    @Autowired
    private ICartRepository iCartRepository;
    @Override
    public Iterable<Cart> findAll() {
        return iCartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return iCartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        iCartRepository.deleteById(id);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return iCartRepository.findById(id);
    }

    @Override
    public Iterable<Cart> findCartByIdUser(User user) {
        return iCartRepository.findCartByIdUser(user);
    }
}
