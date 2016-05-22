package me.codaline.dao;

import me.codaline.model.User;
import me.codaline.model.UserRole;

import java.util.List;

public interface UserDao {

    User findByUserName(String username);
    void saveUser(User user,UserRole userRole);
    List<User> getUsers();
    void setAccess(String username,boolean status);

}