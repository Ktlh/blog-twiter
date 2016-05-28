package me.codaline.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

import me.codaline.model.CrunchifyFileUpload;
import me.codaline.model.Post;
import me.codaline.model.actions;
import me.codaline.service.ImageService;
import me.codaline.service.PostService;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Controller
public class PostController {
    @Autowired
    PostService service;
    @Autowired
    ImageService imageService;



    @RequestMapping(value = "user/savefiles", method = RequestMethod.POST)
    @ResponseBody
    String savefiles(@ModelAttribute("uploadForm") CrunchifyFileUpload uploadForm,
                     HttpServletRequest request) throws IllegalStateException, IOException, InterruptedException {
        imageService.saveImages(request, uploadForm);
        TimeUnit.SECONDS.sleep(1);
        String[] temp2 = imageService.getImages(request);
        return temp2[temp2.length - 1];
    }


    @RequestMapping(value = "user/deletePost", method = RequestMethod.POST)
    String deletePost(ModelMap model, int id, String page) {
        String date = new Date(System.currentTimeMillis()).toString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            service.deletePost(id);
            service.setAction(userDetail.getUsername(), "delete post with id:" + id, null, date);
            List<Post> posts = service.getPosts(userDetail.getUsername());
            model.addAttribute("posts", posts);
            if (page != null)
                model.addAttribute("page", page);
            else model.addAttribute("page", 1);
            model.addAttribute("pages", (posts.size() / 2) + posts.size() % 2);
            return "index2";
        }
        return "index2";
    }


    @RequestMapping(value = "user/Update", method = RequestMethod.GET)
    String updatePost(ModelMap modelMap, HttpServletRequest request, int id) {
        if (id != 0) {
            Post post = service.getPost(id);
            modelMap.addAttribute("post", post);
        }
        modelMap.addAttribute("images", imageService.getImages(request));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        modelMap.addAttribute("currentUser",userDetail.getUsername());
        return "index1";
    }


    @RequestMapping(value = "user/updatePost", method = RequestMethod.POST)
    ModelAndView updatePost(int ID, String title, String context, String image) {
        String date = new Date(System.currentTimeMillis()).toString();
//        try {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = null;
        if (!(auth instanceof AnonymousAuthenticationToken)) {
          userDetail = (UserDetails) auth.getPrincipal();
            if (ID != 0) {
                service.update(ID, title, date, context, image, userDetail.getUsername() );
                service.setAction(userDetail.getUsername(), "modified ", "http://localhost:8080/material?id=" + ID, date);
            } else {
                Post post = service.createPost(title, context, date, image, userDetail.getUsername());
                service.setAction(userDetail.getUsername(), "add new ", "http://localhost:8080/material?id=" + post.getId(), date);
            }

        }
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }


        return new ModelAndView("redirect:/user"+userDetail.getUsername());
    }

    @RequestMapping(value = "material", method = RequestMethod.GET)
    String singlePage(ModelMap model, int id) {
        Post post = service.getPost(id);
        model.addAttribute("post", post);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        model.addAttribute("currentUser",userDetail.getUsername());
        return "singlePage";
    }


}