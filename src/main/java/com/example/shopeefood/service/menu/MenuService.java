package com.example.shopeefood.service.menu;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.repository.IMenuRepository;
import com.example.shopeefood.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService implements IMenuService {

    @Autowired
    private IMenuRepository iMenuRepository;
    @Override
    public Iterable<Menu> findAll() {
        return iMenuRepository.findAll();

    }

    @Override
    public Menu save(Menu menu) {
      return   iMenuRepository.save(menu);
    }

    @Override
    public void remove(Long id) {

        iMenuRepository.deleteById(id);
    }


    @Override
    public Optional<Menu>  findById(Long id) {
        return iMenuRepository.findById(id);
    }
}
