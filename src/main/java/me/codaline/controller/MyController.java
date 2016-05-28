package me.codaline.controller;

import me.codaline.model.Activity;
import me.codaline.model.Post;
import me.codaline.service.EmailService;
import me.codaline.model.User;
import me.codaline.service.ImageService;
import me.codaline.service.PostService;
import me.codaline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.List;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

@Controller
@SessionAttributes({"access", "user"})

public class MyController {
    @Autowired
    PostService postService;
    @Autowired
    ImageService imageService;
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;

    @RequestMapping("/user{userName}")
    String index(ModelMap model, String page, @PathVariable String userName) {
        java.util.List<Post> posts = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            posts =  postService.getPosts(userName);
            model.addAttribute("posts", posts);
            model.addAttribute("userName",userName);
            model.addAttribute("currentUser",userDetail.getUsername());

            model.addAttribute("username", userDetail.getUsername());
            model.addAttribute("access", true);

            if (page != null)
                model.addAttribute("page", page);
            else model.addAttribute("page", 1);
            model.addAttribute("pages", (posts.size() / 2) + posts.size() % 2);
        }


        return "index2";
    }


    @RequestMapping("/")
    String main() {
        return "login";
    }
    @RequestMapping("/login")
    String logIn() {
        return "login";
    }


    @RequestMapping("/registration")
    String registration() {
        return "registration";
    }

    @RequestMapping("/welldone")
    String welldone(){
        return "welldone";
    }
        @RequestMapping("/gallery")
    String gallery(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("images", imageService.getImages(request));
        return "gallery";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    ModelAndView saveUser(ModelMap modelMap, String firstName, String userName, String email, String pass
    ) {
        userService.createUser(userName, pass, email, firstName);

        emailService.send(email, userName);

        postService.createPost("Your  post ","Edit this post or add new","","\\resources\\images\\kitchen_adventurer_cheesecake_brownie.jpg ",userName);

return new ModelAndView("redirect:/welldone");
    }


    @RequestMapping("/getMail/{code}")
    ModelAndView emailGetter(@PathVariable String code) {

        emailService.confirmEmail(code);
return new ModelAndView("redirect:/");
    }


}
