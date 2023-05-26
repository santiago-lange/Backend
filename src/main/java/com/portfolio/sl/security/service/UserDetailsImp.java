package com.portfolio.sl.security.service;

import com.portfolio.sl.security.entity.User;
import com.portfolio.sl.security.entity.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsImp implements UserDetailsService{
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nombreUser) throws UsernameNotFoundException {
           User user = userService.getByNombreUser(nombreUser).get();
           return UserMain.build(user);
    }
}
