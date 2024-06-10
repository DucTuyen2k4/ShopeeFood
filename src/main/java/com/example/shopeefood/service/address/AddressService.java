package com.example.shopeefood.service.address;

import com.example.shopeefood.model.Address;
import com.example.shopeefood.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository iAddressRepository;

    @Override
    public Iterable<Address> findAll() {
        return iAddressRepository.findAll();
    }

    @Override
    public Address save(Address address) {
        return iAddressRepository.save(address);
    }

    @Override
    public void remove(Long id) {
        iAddressRepository.deleteById(id);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return iAddressRepository.findById(id);
    }
}