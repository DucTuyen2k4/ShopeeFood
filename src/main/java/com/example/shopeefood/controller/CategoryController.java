package com.example.shopeefood.controller;

import com.example.shopeefood.model.Category;

import com.example.shopeefood.model.Shop;
import com.example.shopeefood.repository.ICategoryRepository;
import com.example.shopeefood.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @GetMapping()
    public ResponseEntity<List<Category>> getAllMenus() {
        List<Category> categories = (List<Category>) iCategoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }
    @GetMapping("/idCity/{idCity}")
    public ResponseEntity<List<Category>> getCategoryByIdCity(@PathVariable Long idCity){
        List<Category> shopList = iCategoryRepository.findByIdCity(idCity);
        if (shopList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(shopList, HttpStatus.OK);
        }
    }

}
