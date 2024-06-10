package com.example.shopeefood.service.shop;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.repository.IShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShopService implements IShopService{
    @Autowired
    private IShopRepository iShopRepository;
    @Override
    public Iterable<Shop> findAll() {
        return iShopRepository.findAll();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return iShopRepository.findById(id);
    }

    @Override
    public Shop save(Shop shop) {
        return iShopRepository.save(shop);
    }

    @Override
    public void remove(Long id) {
        iShopRepository.deleteById(id);
    }


}

