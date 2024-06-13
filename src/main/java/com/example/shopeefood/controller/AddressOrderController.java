package com.example.shopeefood.controller;

import com.example.shopeefood.model.AddressOrder;
import com.example.shopeefood.repository.IAddressOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addressOrder")

public class AddressOrderController {
    @Autowired
    IAddressOrderRepository iAddressOrderRepository;
    @PostMapping
    public ResponseEntity postAddressOrder(@RequestBody AddressOrder addressOrder) {
        iAddressOrderRepository.save(addressOrder);
        return ResponseEntity.ok().build();
    }
}
