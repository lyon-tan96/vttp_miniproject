package nus.iss.paf.miniproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.paf.miniproject.services.CardService;


@Controller
@RequestMapping("/search")
public class CardSearchController {

    @Autowired
    private CardService cardSvc;

    @GetMapping("/cards")
    public ModelAndView searchCards(@RequestParam String name, HttpSession sess) {

        ModelAndView mvc = new ModelAndView();

        String email = (String)sess.getAttribute("email");

        // searchRepo.insertSearchHistory(name, email);

        System.out.printf(">>>>>> cardName: %s, >>> email: %s", name, email);

        List<String> images = cardSvc.getCards(name, email);

        if (images.isEmpty()) {
            mvc.addObject("name", name.toUpperCase());
            mvc.setViewName("search_error");
            return mvc;
        }

        for (String image : images) {
            System.out.printf(">>>>> image: %s", image);
        }

        mvc.addObject("name", name.toUpperCase());
        mvc.addObject("images", images);
        mvc.setViewName("result");

        return mvc;
    }
    
}
