package me.codaline.controller;

import me.codaline.service.PostService;
import me.codaline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    PostService service;
    @Autowired
    UserService userService;



    @RequestMapping(value = "/sccss")
    public ModelAndView defaul() {
        String date = new Date(System.currentTimeMillis()).toString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = null ;
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            userDetail = (UserDetails) auth.getPrincipal();
            service.setAction(userDetail.getUsername(), "login in ", null, date);
            userService.upActivity(userDetail.getUsername());
            if(service.getPosts(userDetail.getUsername()).size()<1)
            {
                service.createPost("Your  post ","Edit this post or add new","","\\resources\\images\\kitchen_adventurer_cheesecake_brownie.jpg ",userDetail.getUsername());

            }
        }
        return new ModelAndView("redirect:/user"+userDetail.getUsername());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }
//	@RequestMapping(value = "/**")
//	public String pageNotFound(){
//		return "404";
//	}

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();


            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    String pageNotFound() {
        return "404";
    }

}