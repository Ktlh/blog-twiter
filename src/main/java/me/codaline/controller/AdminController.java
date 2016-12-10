package me.codaline.controller;

import com.sun.istack.internal.Nullable;
import me.codaline.model.Activity;
import me.codaline.model.User;
import me.codaline.model.actions;
import me.codaline.service.PostService;
import me.codaline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    PostService service;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;


    @RequestMapping(value = "admin/userList", method = RequestMethod.GET)
    String uList(ModelMap model) {
        java.util.List<User> users = userService.getUsers();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        model.addAttribute("currentUser", userDetail.getUsername());
        model.addAttribute("users", users);
        return "userList";
    }

    @RequestMapping(value = "admin/changeAccesss", method = RequestMethod.POST)
    void ban(String username, boolean status) {
        userService.setAccess(username, status);
    }

    @RequestMapping(value = "admin/stat", method = RequestMethod.GET)
    String statt(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        model.addAttribute("currentUser", userDetail.getUsername());
//        ArrayList<Activity> statistica=(ArrayList<Activity>) userService.getActivities();
//model.addAttribute("stat",statistica);
        return "usersStat";
    }

    @RequestMapping(value = "admin/resetStat", method = RequestMethod.POST)
    void resetStat() {
        userService.cleanActivities();
    }

    @RequestMapping(value = "admin/stat", method = RequestMethod.POST)
    @ResponseBody
    String statt2() {
        java.util.List<Activity> statistica = userService.getActivities();
        String strings1 = new String();
        String strings2 = new String();
        for (int i = 0; i < statistica.size(); i++) {
            strings1 += statistica.get(i).getLogcount() + "-";
            strings2 += statistica.get(i).getLogin() + ",";
        }

        return strings1 + "+" + strings2;
    }

    @RequestMapping(value = "admin/users", method = RequestMethod.GET)
    String act(ModelMap model) {
        List<User> users = userService.getUsers();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        model.addAttribute("currentUser", userDetail.getUsername());
        model.addAttribute("users", users);
        return "usersInfoList";
    }

    @RequestMapping(value = "admin/aboutUser/{userName}", method = RequestMethod.GET)
    String aboutUser(ModelMap model, @PathVariable String userName) {
        List<actions> actionses = service.getActions();
        List<actions> actionsesOut = new ArrayList<actions>();
        for (actions act : actionses) {
            if (act.getUser().equals(userName)) {
                actionsesOut.add(act);
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        model.addAttribute("currentUser", userDetail.getUsername());
        model.addAttribute("actions", actionsesOut);
        model.addAttribute("countPosts",postService.getPosts(userName).size());
        model.addAttribute("user",userService.getUser(userName));
        return "aboutUser";
    }
}
