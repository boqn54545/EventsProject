package com.example.EventsProject.Controllers;

import com.example.EventsProject.Entities.Ad;
import com.example.EventsProject.Entities.User;
import com.example.EventsProject.Repositories.AdsRepository;
import com.example.EventsProject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/event")
public class AdsController {
    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllAds(Model m) {
        Iterable<Ad> ads = adsRepository.findAll();
        m.addAttribute("ads", ads);
        return "index";
    }

    @GetMapping("createAds")
    public String createAd(Model m) {
        Ad ad = new Ad();
        m.addAttribute("ad", ad);
        return "/createAds";
    }

    @PostMapping("submit")
    private ModelAndView saveAd(@Valid Ad ad, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/createAd");
        } else {
            String username = principal.getName();
            User user = userRepository.findByName(username);
            ad.setUser(user);
            adsRepository.save(ad);
            return new ModelAndView("redirect:/event");
        }
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteAd(@PathVariable(name = "id") Long id) {
        adsRepository.deleteById(id);
        return new ModelAndView("redirect:/event");
    }

    @PostMapping("/edit/{id}")
    public String editHotel(@PathVariable(name = "id") Long id, Model m) {
        m.addAttribute("ads", adsRepository.findById(id));
        return "/edit";
    }

}


