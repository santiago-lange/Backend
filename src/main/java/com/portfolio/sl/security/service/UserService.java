package com.portfolio.sl.security.service;

import com.portfolio.sl.security.entity.User;
import com.portfolio.sl.security.repository.IUserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
    @Autowired
    IUserRepository iuserRepository;
    
    public Optional<User> getByNombreUser(String nombreUser){
        return iuserRepository.findByNombreUser(nombreUser);
    }
    
    public boolean existsByNombreUser (String nombreUser){
        return iuserRepository.existsByNombreUser(nombreUser);
    }
    
        public boolean existsByEmail (String email){
        return iuserRepository.existsByEmail(email);
    }
        
    public void save(User user){
        iuserRepository.save(user);
    }
}
