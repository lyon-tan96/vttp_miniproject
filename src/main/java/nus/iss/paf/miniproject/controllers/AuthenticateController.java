package nus.iss.paf.miniproject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import nus.iss.paf.miniproject.services.UsersService;

@Controller
@RequestMapping("/authenticate")
public class AuthenticateController {
    
    @Autowired
    private UsersService usersSvc;

    @GetMapping("/logout")
    public String getLogout(HttpSession sess) {
        sess.invalidate();
        return "index";
    }

    @PostMapping
    public ModelAndView postMethodName(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession sess) {

        System.out.printf(">>> name: %s, email: %s, password = %s\n", name, email, password);

        ModelAndView mvc = new ModelAndView();
        if (!usersSvc.authenticate(name, email, password)) {
            mvc.setViewName("error");
            mvc.setStatus(HttpStatus.FORBIDDEN);
            return mvc;
        } else {
            sess.setAttribute("email", email);
            sess.setAttribute("name", name.toUpperCase());
            mvc = new ModelAndView("redirect:/protected/search.html");
        }
        return mvc;
    }
}
