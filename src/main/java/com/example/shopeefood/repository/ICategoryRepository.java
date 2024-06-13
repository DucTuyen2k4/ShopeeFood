package com.example.shopeefood.repository;

import com.example.shopeefood.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.idCity.id = :cityId")
    List<Category> findByIdCity(@Param("cityId") Long cityId);
}

