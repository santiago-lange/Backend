package com.portfolio.sl.service;

import com.portfolio.sl.entity.User;
import com.portfolio.sl.interfaz.UserInterfaz;
import com.portfolio.sl.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserImp implements UserInterfaz{
    @Autowired UserRepository userRepository;
    
    @Override
    public List<User> getUser() {
        List<User> user= userRepository.findAll();
        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
    
}
