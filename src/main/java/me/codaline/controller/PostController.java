package me.codaline.controller;


import me.codaline.model.Post;
import me.codaline.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService service;


    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    String createPost(ModelMap model, String title, String context, String date, String image) {
        service.createPost(title, context, date, image);
        List<Post> posts = service.getPosts();
        model.addAttribute("posts", posts);
        return "index2";
    }


    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    String deletePost(ModelMap model, int id) {
        service.deletePost(id);
        List<Post> posts = service.getPosts();
        model.addAttribute("posts", posts);
        return "index2";
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    String updatePost(ModelAndView model, int id, String title, String context, String date, String image) {
        Post post = service.update(id, title, context, date, image);
        model.addObject("post", post);
        return "index2";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    String getPosts(ModelMap model) {

        List<Post> posts = service.getPosts();
        model.addAttribute("posts", posts);

        return "posts";
    }
}