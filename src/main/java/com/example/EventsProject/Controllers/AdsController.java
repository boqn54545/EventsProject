package com.example.EventsProject.Controllers;
import com.example.EventsProject.Controllers.Services.AdsService;
import com.example.EventsProject.Entities.Ad;
import com.example.EventsProject.Entities.User;
import com.example.EventsProject.Enums.InterestsEnum;
import com.example.EventsProject.Repositories.AdsRepository;
import com.example.EventsProject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/event")
public class AdsController {
    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdsService adsService;

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

    @GetMapping("/search")
    public String searchAds(Model model,
                            @RequestParam(required=false) String title,
                            @RequestParam(required=false) Integer priceMin,
                            @RequestParam(required=false) Integer priceMax,
                            @RequestParam(required=false) String city,
                            @RequestParam(required=false) InterestsEnum interest,
                            @RequestParam(required=false) Integer minAge,
                            @RequestParam(required=false) Integer maxAge) {
        List<Ad> ads = adsService.searchAds(title, priceMin, priceMax, city, interest, minAge, maxAge);
        model.addAttribute("ads", ads);
        return "index";
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
    public String editHotel(@PathVariable(name = "id") Long id, Model m){
        m.addAttribute("ad",adsRepository.findById(id));
        return "/edit";
    }



}
