package com.example.shopeefood.service.detailcart;
import com.example.shopeefood.model.DetailCart;
import com.example.shopeefood.model.Product;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.model.User;
import com.example.shopeefood.service.IGenerateService;

public interface IDetailCartService extends IGenerateService<DetailCart> {
    Iterable<DetailCart> findAllByShopAndCart(Shop shop, User user);
    DetailCart findDetailCartByProduct(Product product);
}
