package nus.iss.paf.miniproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/register")
    public String redirectToRegister() {
        return "register";
    }

    @RequestMapping("/search.html")
    public String redirectToSearch() {
        return "search.html";
    }

    @RequestMapping("/index")
    public String redirectToIndex() {
        return "index.html";
    }
    
}
