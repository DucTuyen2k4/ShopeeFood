package com.example.shopeefood.repository;

import com.example.shopeefood.model.Address;
import com.example.shopeefood.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {

    List<Address> findAllByUserId(Long idUser);
}
