package com.example.EventsProject.Controllers;

import com.example.EventsProject.Entities.Ad;
import com.example.EventsProject.Repositories.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
}

