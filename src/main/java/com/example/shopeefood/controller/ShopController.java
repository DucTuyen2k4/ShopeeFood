package com.example.shopeefood.controller;

import com.example.shopeefood.model.*;
import com.example.shopeefood.repository.IShopRepository;
import com.example.shopeefood.service.category.ICategoryService;
import com.example.shopeefood.service.city.ICityService;
import com.example.shopeefood.service.shop.IShopService;
import com.example.shopeefood.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/shops")
public class ShopController {
    @Autowired
    private IShopService iShopService;
    @Autowired
    private IShopRepository shopRepository;
    @Autowired
    private ICategoryService iCategoryService;

    @Value("/home/nguyenhuugiang19072004/IdeaProjects/ShopeeFood-Nh-m-3-/src/main/resources/static/img/")
    private String fileUpload;
    public MultipartFile multipartFile;
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/findShopByName")
    public ResponseEntity<List<Shop>> findShop(@RequestParam String name) {
        return ResponseEntity.ok(shopRepository.findAllByNameContaining(name));
    }
    @GetMapping
    public ResponseEntity<List<Shop>> getAllShops(){
        List<Shop> shops = (List<Shop>) iShopService.findAll();
        if (shops.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(shops, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Shop> findById(@PathVariable Long id) {
        Optional<Shop> shopOptional = iShopService.findById(id);
        if (shopOptional.isPresent()) {
            Shop shop = shopOptional.get();
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping()
    public ResponseEntity<Shop> createShop(@ModelAttribute ShopFile shopFile)  throws IOException {
         multipartFile = shopFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(shopFile.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        Shop shop = new Shop(
                shopFile.getId(),
                shopFile.getName(),
                shopFile.getPhoneNumber(),
                shopFile.getAddress(),

                shopFile.getEmail(),
                fileName,
                shopFile.getTimeStart(),
                shopFile.getTimeEnd(),
                shopFile.getIdCity(),
                shopFile.getIdCategory(),
                shopFile.getIdUser(),
                localDateTime,
                localDateTime
        );
        return new ResponseEntity<>(iShopService.save(shop), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @ModelAttribute ShopFile shopFile) throws IOException {
        Optional<Shop> optionalShop = iShopService.findById(id);
        if (!optionalShop.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Shop originalShop = optionalShop.get();
        LocalDateTime localDateTime = LocalDateTime.now();
        MultipartFile multipartFile = shopFile.getImage();
        String fileName = multipartFile.getOriginalFilename();
        if (!multipartFile.isEmpty()) {
            try {
                FileCopyUtils.copy(shopFile.getImage().getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            fileName = originalShop.getImage();  // Giữ nguyên tên tệp nếu không có tệp mới
        }

        Shop updatedShop = new Shop(
                shopFile.getId(),
                shopFile.getName(),
                shopFile.getAddress(),
                shopFile.getPhoneNumber(),
                shopFile.getEmail(),
                fileName,
                shopFile.getTimeStart(),
                shopFile.getTimeEnd(),
                shopFile.getIdCity(),
                shopFile.getIdCategory(),
                originalShop.getIdUser(),
                originalShop.getCreatedAt(),
                localDateTime
        );

        return new ResponseEntity<>(iShopService.save(updatedShop), HttpStatus.OK);
    }
    @GetMapping("/findByIdCategory/{id}")
    public ResponseEntity<List<Shop>> findShopByIdCategory(@PathVariable Long id){
        Optional<Category> optionalCategory = iCategoryService.findById(id);
        List<Shop> shopList = shopRepository.findShopByIdCategory(optionalCategory.get());
        if (shopList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(shopList, HttpStatus.OK);
        }
    }
}





