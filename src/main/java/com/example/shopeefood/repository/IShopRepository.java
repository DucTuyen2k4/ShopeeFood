package com.example.shopeefood.repository;

import com.example.shopeefood.model.Category;
import com.example.shopeefood.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByNameContaining(String name);
    List<Shop> findShopByIdCategory(Category category);
}


