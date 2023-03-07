package com.example.EventsProject.Controllers;

import com.example.EventsProject.Entities.Ad;
import com.example.EventsProject.Repositories.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/event")
public class AdsController {
    @Autowired
    private AdsRepository adsRepository;

    @GetMapping
    public String getAllAds(Model m) {
        Iterable<Ad> ads = adsRepository.findAll();
        m.addAttribute("ads", ads);
        return "index";
    }
    @GetMapping("createAds")
    public String createAd(Model m){
        Ad ad = new Ad();
        m.addAttribute("ad",ad);
        return "/createAds";
    }
    @PostMapping("submit")
    private ModelAndView saveHotel(@Valid Ad ad, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("/createAd");
        }else{
            adsRepository.save(ad);
            return new ModelAndView("redirect:/event");
        }
    }

}

