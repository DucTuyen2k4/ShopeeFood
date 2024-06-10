package com.example.shopeefood.service.product;

import com.example.shopeefood.model.Product;
import com.example.shopeefood.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGenerateService<Product> {
  Page<Product> findAllByName (Pageable pageable, String name);
}
