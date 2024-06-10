package com.example.shopeefood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "detailCart")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DetailCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "product_id")
    @ManyToOne()
    private Product product;
    private int quantity;
    @JoinColumn(name = "shop_id")
    @ManyToOne()
    private Shop shop;
    @JoinColumn(name = "cart_id")
    @ManyToOne()
    private Cart cart;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public DetailCart() {
    }

    public DetailCart(Product product, int quantity, Shop shop, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.shop = shop;
        this.cart = cart;
    }
}