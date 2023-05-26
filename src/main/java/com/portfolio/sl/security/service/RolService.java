package com.portfolio.sl.security.service;

import com.portfolio.sl.security.entity.Rol;
import com.portfolio.sl.security.enums.RolName;
import com.portfolio.sl.security.repository.IRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolService {
    @Autowired
    IRolRepository irolRepository;
    
    public Optional<Rol> getByRolName(RolName rolName){
        return irolRepository.findByRolName(rolName);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
