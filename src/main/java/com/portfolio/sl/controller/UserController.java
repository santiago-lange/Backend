package com.portfolio.sl.controller;

import com.portfolio.sl.entity.User;
import com.portfolio.sl.interfaz.UserInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired UserInterfaz userInterfaz;
    
    @GetMapping("user/get")
    public List<User> getUser(){
        return userInterfaz.getUser();
    }
    
    @PostMapping("/user/post")
    public String postUser(@RequestBody User user){
        userInterfaz.saveUser(user); 
            return "New user";
        }
    
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userInterfaz.deleteUser(id);
        return "deleted user";
    }
                         
   @PutMapping("/user/edit/{id}")
   public User editUser(@PathVariable Long id,
                     @RequestParam("nombre") String nuevoNombre,
                     @RequestParam("apellido") String nuevoApellido) {
    User user = userInterfaz.findUser(id);
    
    user.setNombre(nuevoNombre);
    user.setApellido(nuevoApellido);
    
    userInterfaz.saveUser(user);
    return user;
    }
}
