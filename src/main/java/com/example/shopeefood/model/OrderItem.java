package com.example.shopeefood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;
    private String note;
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
   
    public OrderItem() {
    }


    public OrderItem(long id, Product product, Shop shop, int quantity, Cart cart, Order order, String note) {
        this.id = id;
        this.product = product;
        this.shop = shop;
        this.quantity = quantity;
        this.cart = cart;
        this.order = order;
        this.note = note;
    }

    public OrderItem(Long id, Product product, int quantity, Shop shop, Cart cart) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.shop = shop;
        this.cart = cart;
    }

}
