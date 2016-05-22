package me.codaline.dao;

import me.codaline.model.User;
import me.codaline.model.UserRole;


public interface UserDao {

    User findByUserName(String username);
    void saveUser(User user,UserRole userRole);

}