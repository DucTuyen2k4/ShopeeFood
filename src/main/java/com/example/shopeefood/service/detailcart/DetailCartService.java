package com.example.shopeefood.service.detailcart;

import com.example.shopeefood.model.DetailCart;
import com.example.shopeefood.model.Product;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.model.User;
import com.example.shopeefood.repository.IDetailCartRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DetailCartService implements IDetailCartService {
    @Autowired
    private IDetailCartRepository iDetailCartRepository;
    @Override
    public Iterable<DetailCart> findAll() {
        return iDetailCartRepository.findAll();
    }

    @Override
    public DetailCart save(DetailCart detailCart) {
        return iDetailCartRepository.save(detailCart);
    }

    @Override
    public void remove(Long id) {
        iDetailCartRepository.deleteById(id);
    }

    @Override
    public Optional<DetailCart> findById(Long id) {
        return iDetailCartRepository.findById(id);
    }

    @Override
    public Iterable<DetailCart> findAllByShopAndCart(Shop shop, User user) {
        return iDetailCartRepository.findAllByShopAndCart(shop,user);
    }
    @Override
    public DetailCart findDetailCartByProduct(Product product) {
        return iDetailCartRepository.findDetailCartByProduct(product);
    }
}