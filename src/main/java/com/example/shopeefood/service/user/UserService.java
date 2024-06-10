package com.example.shopeefood.service.user;

import com.example.shopeefood.model.User;
import com.example.shopeefood.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public Iterable<User> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public User save(User user) {

        return iUserRepository.save(user);
    }
    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }

}

