package nus.iss.paf.miniproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.paf.miniproject.models.User;
import nus.iss.paf.miniproject.repositories.UsersRepositories;
import nus.iss.paf.miniproject.services.UserException;
import nus.iss.paf.miniproject.services.UsersService;

import static nus.iss.paf.miniproject.models.ConvertUtils.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/newuser")
public class AddUserController {

    @Autowired
    private UsersService usersSvc;

    @Autowired
    private UsersRepositories userRepo;

    @PostMapping
    public ModelAndView postUser(@RequestBody MultiValueMap<String, String> form, HttpSession sess) {

        User user = convert(form);

        ModelAndView mvc = new ModelAndView();

        if (userRepo.findUserByEmail(user.getEmail()).isPresent()) {
            mvc.addObject("email", user.getEmail());
            mvc.setViewName("existing");
            return mvc;
        }
        
        try {
            usersSvc.addNewUser(user);
        } catch (UserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        sess.setAttribute("name", user.getName());
        sess.setAttribute("email", user.getEmail());
        mvc = new ModelAndView("redirect:/protected/search.html");
        System.out.printf(">>> userid: %s, >>> name: %s" , user.getUserId(), user.getName());
        mvc.addObject("name", user.getName().toUpperCase());

        return mvc;
    }
    
}
