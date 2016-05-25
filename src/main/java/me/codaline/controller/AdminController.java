package me.codaline.controller;

import me.codaline.model.Activity;
import me.codaline.model.User;
import me.codaline.model.actions;
import me.codaline.service.PostService;
import me.codaline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class AdminController {
    @Autowired
    PostService service;
    @Autowired
    UserService userService;


    @RequestMapping(value = "admin/userList", method = RequestMethod.GET)
    String uList(ModelMap model) {
        java.util.List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "userList";
    }
    @RequestMapping(value = "admin/changeAccesss", method = RequestMethod.POST)
    void ban(String username, boolean status) {
        userService.setAccess(username, status);
    }
    @RequestMapping(value = "admin/stat", method = RequestMethod.GET)
    String statt(ModelMap model) {
//        ArrayList<Activity> statistica=(ArrayList<Activity>) userService.getActivities();
//model.addAttribute("stat",statistica);
        return "usersStat";
    }
    @RequestMapping(value = "admin/resetStat", method = RequestMethod.POST)
    void resetStat(){
        userService.cleanActivities();
    }

    @RequestMapping(value = "admin/stat",method = RequestMethod.POST)
    @ResponseBody
    String statt2() {
        java.util.List<Activity> statistica = userService.getActivities();
        String strings1=new String();
        String strings2=new String();
        for (int i = 0; i < statistica.size(); i++) {
            strings1 += statistica.get(i).getLogcount()+"-";
            strings2 += statistica.get(i).getLogin()+",";
        }

        return strings1+"+"+strings2;
    }
    @RequestMapping(value = "admin/actions", method = RequestMethod.GET)
    String act(ModelMap model) {
        List<actions> actionses = service.getActions();
        model.addAttribute("actions", actionses);
        return "actionsList";
    }
}
