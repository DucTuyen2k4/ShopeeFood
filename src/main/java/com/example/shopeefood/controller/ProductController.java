package com.example.shopeefood.controller;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Product;
import com.example.shopeefood.model.ProductFile;

import com.example.shopeefood.model.User;
import com.example.shopeefood.repository.IProductRepository;
import com.example.shopeefood.repository.IUserRepository;
import com.example.shopeefood.service.menu.IMenuService;
import com.example.shopeefood.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Value("/home/nguyenhuugiang19072004/IdeaProjects/ShopeeFood-Nh-m-3-/src/main/resources/static/img/")


    private String fileUpload;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IMenuService iMenuService;
    @Autowired
    IProductRepository productRepository;
    @Autowired
    IUserRepository iUserRepository;
    @GetMapping("/FindByPByName/{id}")
    public ResponseEntity<List<Product>> findByPName(@PathVariable Long id,@RequestParam("productName") String productName) {
        List<Product>list=productRepository.findFoodByMenuIdAndName(id, productName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/ProductListByMenuId/{id}")
    public ResponseEntity<List<Product>> getListProduct(@PathVariable Long id) {
        List<Product> list=productRepository.findFoodByMenuId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/FindByPByNameAndPage/{id}")
    public ResponseEntity<Page<Product>> findByPNameAndPage(@PathVariable Long id,
                                                            @RequestParam( name = ("productName"),  required = false) String productName,
                                                            @PageableDefault(value = 6) Pageable pageable) {
        Page<Product>list=productRepository.findFoodByMenuIdAndNameAndPage(id, productName,pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setStatus(productDetails.getStatus());
            productRepository.save(existingProduct);
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/like-exists")
    public ResponseEntity<Boolean> checkUserLikeExists(@RequestParam Long userId, @RequestParam Long productId) {
        boolean exists = productRepository.existsByUserIdAndProductId(userId, productId);
        return ResponseEntity.ok(exists);
    }
    @PostMapping("/plus-like")
    public ResponseEntity<?> addLike(@RequestParam Long userId, @RequestParam Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            Set<User> users = product.getUsers();

            User user = iUserRepository.findById(userId).get();
            users.add(user);

            product.setLike_product(product.getLike_product() + 1);
            productRepository.save(product);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete-like")
    public ResponseEntity<?> deleteLike(@RequestParam Long userId, @RequestParam Long productId) {
        productRepository.deleteByUserIdAndProductId(userId, productId);
        Optional<Product> product= productRepository.findById(productId);
        product.get().setLike_product(product.get().getLike_product()-1);
        productRepository.save(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> saveProduct(@ModelAttribute ProductFile productFile) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            MultipartFile multipartFile = productFile.getImage();
            String fileName = multipartFile.getOriginalFilename();
            FileCopyUtils.copy(productFile.getImage().getBytes(), new File(fileUpload + fileName));

            Product product = new Product(
                    productFile.getId(),
                    productFile.getName(),
                    productFile.getPrice(),
                    productFile.getQuantity(),
                    fileName,
                    productFile.getDetail(),
                    productFile.getMenus(),
                    localDateTime,
                    localDateTime);
            product.setStatus(1);

            product = iProductService.save(product);
            Set<Menu> menuSet = new HashSet<>();
            product.setMenus(menuSet);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CREATED) ;

        }
    }
    @GetMapping("/detailProduct/{idProduct}")
    public ResponseEntity<Product> detailProduct(@PathVariable Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}