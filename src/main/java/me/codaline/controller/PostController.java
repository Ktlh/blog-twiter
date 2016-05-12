package me.codaline.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

import me.codaline.model.CrunchifyFileUpload;
import me.codaline.model.Post;
import me.codaline.service.ImageService;
import me.codaline.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;

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


    @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
        @ResponseBody
    String savefiles(
            @ModelAttribute("uploadForm") CrunchifyFileUpload uploadForm,
           // Model map,
            HttpServletRequest request) throws IllegalStateException, IOException, InterruptedException {


        imageService.saveImages(request, uploadForm);
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

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    String createPost(ModelMap model, String title, String context, String date, String image, String page) {
        service.createPost(title, context, date, image);
        List<Post> posts = service.getPosts();
        model.addAttribute("posts", posts);
        if (page != null)
            model.addAttribute("page", page);
        else model.addAttribute("page", 1);
        model.addAttribute("pages", (posts.size() / 2) + posts.size() % 2);
        return "index2";
    }


    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    String deletePost(ModelMap model, int id, String page) {
        service.deletePost(id);
        List<Post> posts = service.getPosts();
        model.addAttribute("posts", posts);
        if (page != null)
            model.addAttribute("page", page);
        else model.addAttribute("page", 1);
        model.addAttribute("pages", (posts.size() / 2) + posts.size() % 2);
        return "index2";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    void test() {
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    String updatePost(ModelAndView model, int id, String title, String context, String date, String image, String page) {
        Post post = service.update(id, title, context, date, image);
        //   model.addObject("post", post);
        return "index2";
    }


}