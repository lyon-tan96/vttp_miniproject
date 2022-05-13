package nus.iss.paf.miniproject.controllers;

import java.util.List;

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
    public ModelAndView searchCards(@RequestParam String name) {

        ModelAndView mvc = new ModelAndView();

        System.out.printf(">>>>> name: %s", name);

        List<String> images = cardSvc.getCards(name);

        for (String image : images) {
            System.out.printf(">>>>> image: %s", image);
        }

        mvc.addObject("name", name);
        mvc.addObject("images", images);
        mvc.setViewName("result");

        return mvc;
    }
    
}
