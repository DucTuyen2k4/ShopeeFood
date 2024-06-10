package com.example.shopeefood.repository;

import com.example.shopeefood.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findById(long id);
}
