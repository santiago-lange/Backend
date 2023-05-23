package com.portfolio.sl.interfaz;

import com.portfolio.sl.entity.User;
import java.util.List;

public interface UserInterfaz {
    public List<User> getUser();
    
    public void saveUser(User user);
    
    public void deleteUser(Long id);
    
    public User findUser(Long id);
}
