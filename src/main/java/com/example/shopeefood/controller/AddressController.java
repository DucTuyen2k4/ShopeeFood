package com.example.shopeefood.controller;

import com.example.shopeefood.model.Address;
import com.example.shopeefood.model.Order;
import com.example.shopeefood.model.User;
import com.example.shopeefood.repository.IAddressRepository;
import com.example.shopeefood.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {
    @Autowired
    private IAddressRepository iAddressRepository;
    @Autowired
    private IUserService iUserService;

    @GetMapping("/address/{idAddress}")
    private  ResponseEntity<Address> showAddressById(@PathVariable Long idAddress){
        Address address = iAddressRepository.findById(idAddress).get();
        return new ResponseEntity<>(address,HttpStatus.OK);
    }

    @PostMapping("/{idUser}")
    private ResponseEntity<Address> addAddress(@PathVariable Long idUser, @RequestBody Address address){
        User user = iUserService.findById(idUser).get();
        address.setUser(user);
        address= iAddressRepository.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @GetMapping({"/{idUser}"})
    private  ResponseEntity<List<Address>> showAddress(@PathVariable Long idUser){
        List<Address> addressList = iAddressRepository.findAllByUserId(idUser);
        return new ResponseEntity<>(addressList,HttpStatus.OK);
    }

    @PutMapping("{idAddress}")
    private ResponseEntity<Address> updateAddress( @PathVariable long idAddress,@RequestBody Address address){
        address.setId(idAddress);
        address= iAddressRepository.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
    @DeleteMapping("/{idAddress}")
    public ResponseEntity<Order> deleteOrder(@PathVariable long idAddress) {
        iAddressRepository.deleteById(idAddress);
        return ResponseEntity.ok().build();

    }
}