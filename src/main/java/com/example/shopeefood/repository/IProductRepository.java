package com.example.shopeefood.repository;

import com.example.shopeefood.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p JOIN p.menus m WHERE m.id = :menuId AND p.name LIKE %:productName%")
    List<Product> findFoodByMenuIdAndName(@Param("menuId") Long menuId, @Param("productName") String productName);

    @Query("SELECT f FROM Product f JOIN f.menus m WHERE m.id = :menuId")
    List<Product> findFoodByMenuId(@Param("menuId") Long menuId);

    @Query("SELECT p FROM Product p JOIN p.menus m WHERE m.id = :menuId AND p.name LIKE %:productName%")
    Page<Product> findFoodByMenuIdAndNameAndPage(@Param("menuId") Long menuId, @Param("productName") String productName, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Product p JOIN p.users u WHERE p.id = :productId AND u.id = :userId")
    boolean existsByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM like_user WHERE product_id = :productId AND user_id = :userId", nativeQuery = true)
    void deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    Page<Product> findAllByNameContaining(Pageable pageable, String name);
    List<Product> findByMenusId(long menuId);

}