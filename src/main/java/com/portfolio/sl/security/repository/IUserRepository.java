package com.portfolio.sl.security.repository;

import com.portfolio.sl.security.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByNombreUser(String nombreUser);
    
    boolean existsByNombreUser(String nombreUser);
    boolean existsByEmail(String email);
}
