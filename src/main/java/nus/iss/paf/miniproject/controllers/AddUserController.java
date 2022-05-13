package nus.iss.paf.miniproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.paf.miniproject.models.User;
import nus.iss.paf.miniproject.services.UsersService;

import static nus.iss.paf.miniproject.models.ConvertUtils.*;

@Controller
@RequestMapping("/newuser")
public class AddUserController {

    @Autowired
    private UsersService usersSvc;

    @PostMapping
    public ModelAndView postUser(@RequestBody MultiValueMap<String, String> form) {

        User user = convert(form);

        ModelAndView mvc = new ModelAndView();

        usersSvc.addNewUser(user);
        System.out.printf(">>> userid: %s" , user.getUserId());
        mvc.addObject("name", user.getName());
        mvc.setViewName("search");

        return mvc;
    }
    
}
