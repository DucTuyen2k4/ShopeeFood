package com.example.shopeefood.service.product;

import com.example.shopeefood.model.Product;
import com.example.shopeefood.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
return iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {

        iProductRepository.deleteById(id);
    }


    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }
    @Override
    public Page<Product> findAllByName(Pageable pageable,String name) {
        return iProductRepository.findAllByNameContaining(pageable,name);
    }
}
