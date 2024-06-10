package com.example.shopeefood.repository;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShopRepository extends JpaRepository<Shop, Long> {
    @Query("SELECT s FROM Shop s WHERE s.idCity.id = :cityId")
    List<Shop> findShopByIdCity(@Param("cityId") Long cityId);
}

