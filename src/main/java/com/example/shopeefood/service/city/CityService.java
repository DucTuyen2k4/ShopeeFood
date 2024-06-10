package com.example.shopeefood.service.city;

import com.example.shopeefood.model.City;
import com.example.shopeefood.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CityService implements ICityService{
    @Autowired
    private ICityRepository iCityRepository;
    @Override
    public Iterable<City> findAll() {
        return iCityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return iCityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        return iCityRepository.save(city);
    }
    @Override
    public void remove(Long id) {
        iCityRepository.deleteById(id);
    }
}

