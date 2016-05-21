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


    @Secured("hasRole('ROLE_USER')")
    @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    @ResponseBody
    String savefiles(@ModelAttribute("uploadForm") CrunchifyFileUpload uploadForm,
                     // Model map,
                     HttpServletRequest request) throws IllegalStateException, IOException, InterruptedException {

//        String date = new Date(System.currentTimeMillis()).toString();
        imageService.saveImages(request, uploadForm);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            UserDetails userDetail = (UserDetails) auth.getPrincipal();
//            List<MultipartFile> crunchifyFiles = uploadForm.getFiles();
//            for (MultipartFile multipartFile : crunchifyFiles) {
//                String fileName = multipartFile.getOriginalFilename();
//                service.setAction(userDetail.getUsername(), "upload file with name:" +fileName, date);
//            }
//        }
        TimeUnit.SECONDS.sleep(1);
        // map.addAttribute("images", imageService.getImages(request));
        String[] temp2 = imageService.getImages(request);

//        return "<script language=\"JavaScript\">\n" +
//                "<!--\n" +
//                " alert(\"\");" +
//                "//-->\n" +
//                "</script>";
//        return "<script language=\"JavaScript\">\n" +
//                "<!--\n" +
//                "#(\"#image_container\";\n" +
////                "alert(" + temp[temp.length - 1] + ")\n"+
//                ".append( '<img style=\"margin: 10px\" width=\"100px\" height=\"100px\" src=\"<c:url value=" + temp[temp.length - 1] + "/> \"/>' )\n" +
//                "$(\"#image_container\").html(data);\n"+
//                "//-->\n" +
//                "</script>";
//return "index1";
        return temp2[temp2.length - 1];
    }

//    @RequestMapping(value = "/Update", method = RequestMethod.POST)
//    String createPost(ModelMap model, String title, String context, String date, String image, String page) {
//        service.createPost(title, context, date, image);
//        List<Post> posts = service.getPosts();
//        model.addAttribute("posts", posts);
//        if (page != null)
//            model.addAttribute("page", page);
//        else model.addAttribute("page", 1);
//        model.addAttribute("pages", (posts.size() / 2) + posts.size() % 2);
//        return "index2";
//    }


    @Secured("hasRole('ROLE_USER')")
    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    String deletePost(ModelMap model, int id, String page) {
        String date = new Date(System.currentTimeMillis()).toString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            service.deletePost(id);
            service.setAction(userDetail.getUsername(), "delete post with id:" + id, date);
            List<Post> posts = service.getPosts();
            model.addAttribute("posts", posts);
            if (page != null)
                model.addAttribute("page", page);
            else model.addAttribute("page", 1);
            model.addAttribute("pages", (posts.size() / 2) + posts.size() % 2);
//        new Date(System.currentTimeMillis());
            return "index2";
        }
        return "index2";
    }

    @Secured("hasRole('ROLE_USER')")
    @RequestMapping(value = "/Update", method = RequestMethod.GET)
    String updatePost(ModelMap modelMap, HttpServletRequest request, int id) {
        if (id != 0) {
            Post post = service.getPost(id);
            modelMap.addAttribute("post", post);
        }
        modelMap.addAttribute("images", imageService.getImages(request));
        return "index1";
    }

    @Secured("hasRole('ROLE_USER')")
    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    ModelAndView updatePost(int ID, String title, String context, String image) {
        String date = new Date(System.currentTimeMillis()).toString();
//        try {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (ID != 0) {
                service.update(ID, title, date, context, image);
                service.setAction(userDetail.getUsername(), "modify post with id:" + ID, date);
            } else {
                service.createPost(title, context, date, image);
                service.setAction(userDetail.getUsername(), "add new post", date);
            }

        }
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }


        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "material", method = RequestMethod.GET)
    String singlePage(ModelMap model, int id) {
        Post post = service.getPost(id);
        model.addAttribute("post", post);
        return "singlePage";
    }
    @RequestMapping(value = "actions",method = RequestMethod.GET)
    String act(ModelMap model){
        List<actions> actionses=service.getActions();
        model.addAttribute("actions",actionses);
        return "actionsList";
    }
}