package com.example.shopeefood.service.orderItem;

import com.example.shopeefood.model.*;
import com.example.shopeefood.service.IGenerateService;

import java.util.List;

public interface IOrderItemService extends IGenerateService<OrderItem> {
    Iterable<OrderItem> findAllByShopAndCart(Shop shop, User user);

    OrderItem findOrderItemByProduct(Product product);

    List<OrderItem> findByOrderId(long idOrder);
}