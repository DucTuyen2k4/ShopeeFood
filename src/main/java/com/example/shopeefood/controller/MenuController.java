package com.example.shopeefood.controller;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.repository.IMenuRepository;
import com.example.shopeefood.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/menus")
public class MenuController {
    @Autowired
    private IMenuRepository menuRepo;
    @Autowired
    private IMenuService iMenuService;
    @GetMapping("{id}")
    public ResponseEntity<List<Menu>> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(menuRepo.findMenuByIdShop(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = (List<Menu>) iMenuService.findAll();
        if (menus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
}
