package nus.iss.paf.miniproject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/protected/{view}")
public class ProtectedController {

    @GetMapping
    @PostMapping
    public ModelAndView post(@PathVariable String view, HttpSession sess) {

        String name = (String)sess.getAttribute("name");

        ModelAndView mvc = new ModelAndView();
        mvc.setViewName(view);
        mvc.addObject("name", name.toUpperCase());
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }
    
}
