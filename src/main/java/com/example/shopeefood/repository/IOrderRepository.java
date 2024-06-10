package com.example.shopeefood.repository;


import com.example.shopeefood.model.DetailCart;
import com.example.shopeefood.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByOrderItems_Shop_Id(long shopId);
//    List<Order> findDistinctByOrderItems_Shop_Id(long shopId);
    List<Order> findByUserId(long userId);
    List<Order> findByStatusId(long statusId);
    Optional<Order> findById(long orderId);

}

