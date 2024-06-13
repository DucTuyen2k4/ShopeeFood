package com.example.shopeefood.service.orderItem;

import com.example.shopeefood.model.*;
import com.example.shopeefood.repository.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private IOrderItemRepository iOrderItemRepository;
    @Override
    public Iterable<OrderItem> findAll() {
        return iOrderItemRepository.findAll();
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return iOrderItemRepository.save(orderItem);
    }

    @Override
    public void remove(Long id) {
        iOrderItemRepository.deleteById(id);
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return iOrderItemRepository.findById(id);
    }

    @Override
    public Iterable<OrderItem> findAllByShopAndCart(Shop shop, User user) {
        return iOrderItemRepository.findAllByShopAndCart(shop,user);
    }

    @Override
    public OrderItem findOrderItemByProduct(Product product) {
        return iOrderItemRepository.findOrderItemByProduct(product);
    }
    @Override
    public List<OrderItem> findByOrderId(long idOrder) {
        return iOrderItemRepository.findOrderItemByOrderId(idOrder);
    }



}
