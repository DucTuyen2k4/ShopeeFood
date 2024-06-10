package com.example.shopeefood.controller;

import com.example.shopeefood.model.*;
import com.example.shopeefood.repository.*;

import com.example.shopeefood.service.address.IAddressService;

import com.example.shopeefood.service.detailcart.IDetailCartService;
import com.example.shopeefood.service.orderItem.IOrderItemService;
import com.example.shopeefood.service.shop.IShopService;
import com.example.shopeefood.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IShopService iShopService;
    @Autowired
    private IOrderItemService iOrderItemService;
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IDetailCartService iDetailCartService;
    @Autowired
    private IStatusRepository iStatusRepository;
    @Autowired
    private IOrderItemRepository iOrderItemRepository;
    @Autowired
    private IAddressRepository iAddressRepository;

  


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderItemsByOrderId(@PathVariable long orderId) {
        return  new ResponseEntity<>(iOrderRepository.findById(orderId).get(), HttpStatus.OK);

    }
    @GetMapping("/orderItem/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemByOrderId(@PathVariable long orderId) {
        List<OrderItem> orderItems = iOrderItemService.findByOrderId(orderId);
        if (orderItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        }
    }
    @PutMapping("/status/{idOrder}/{statusId}")
    public ResponseEntity<Order> setStatus(@PathVariable long idOrder, @PathVariable long statusId) {
        Optional<Order> optionalOrder = iOrderRepository.findById(idOrder);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Status> optionalStatus = iStatusRepository.findById(statusId);
        if (!optionalStatus.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Order order = optionalOrder.get();
        Status status = optionalStatus.get();
        order.setStatus(status);
        iOrderRepository.save(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders/shop/{shopId}")
    public List<Order> getOrdersByShopId(@PathVariable long shopId) {
        return iOrderRepository.findByOrderItems_Shop_Id(shopId);
    }
    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(iOrderRepository.findByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<Order>> getOrdersByStatusId(@PathVariable long statusId) {
        return new ResponseEntity<>(iOrderRepository.findByStatusId(statusId),HttpStatus.OK);
    }


    @PostMapping("/{idUser}/{idShop}/{idAddress}")
    public ResponseEntity<Order> createOrder(@PathVariable long idShop, @PathVariable long idUser, @PathVariable long idAddress, @RequestBody String note) {
        Optional<User> userOptional = iUserService.findById(idUser);
        Optional<Shop> shopOptional = iShopService.findById(idShop);

        Optional<Address> addressOptional = iAddressRepository.findById(idAddress);


        if (!userOptional.isPresent() || !shopOptional.isPresent() || !addressOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userOptional.get();
        Shop shop = shopOptional.get();
        Address address = addressOptional.get();

        List<OrderItem> orderItems = (List<OrderItem>) iOrderItemService.findAllByShopAndCart(shop, user);
        if (orderItems.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Status> statusOptional = iStatusRepository.findById(1L);
        if (!statusOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Order order = new Order();
        order.setStatus(statusOptional.get());
        order.setUser(user);
        order.setAddress(address);
        for (OrderItem item : orderItems) {
            if (item.getOrder() == null) {
                item.setNote(note);
                order.addOrderItem(item);
            }
        }


        Order savedOrder = iOrderRepository.save(order);
        for (OrderItem orderItem : orderItems) {

            iDetailCartService.remove(orderItem.getId());
        }

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

}
