package me.codaline.service;

import me.codaline.dao.UserDao;
import me.codaline.dao.UserDaoImpl;
import me.codaline.model.User;
import me.codaline.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserService {

    @Autowired
    UserDaoImpl dao;

    public void createUser(String userName, String lastName, String email, String pass) {
        User user = new User();
        user.setEnabled(false);
        user.setPassword(pass);
        user.setUsername(userName);
        UserRole userRole= new UserRole();
        userRole.setRole("USER_ROLE");
        userRole.setUser(user);
        dao.saveUser(user,userRole);


    }

    public List<User> getUsers() {
        return dao.getUsers();
    }
    public void setAccess(String username, boolean status){
        dao.setAccess(username,status);
    }
//    public User getUser(String email, String pass) {
//        return dao.getUser(email, pass);
//    }


}
