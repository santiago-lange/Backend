package com.portfolio.sl.security.repository;

import com.portfolio.sl.security.entity.Rol;
import com.portfolio.sl.security.enums.RolName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolName(RolName rolName);
}
