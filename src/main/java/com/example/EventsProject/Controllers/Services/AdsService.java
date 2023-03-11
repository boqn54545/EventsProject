package com.example.EventsProject.Controllers.Services;

import com.example.EventsProject.Entities.Ad;
import com.example.EventsProject.Enums.InterestsEnum;
import com.example.EventsProject.Repositories.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdsService {

    @Autowired
    private AdsRepository adsRepository;

    public List<Ad> searchAds(String title, Integer priceMin, Integer priceMax, String city, InterestsEnum interest, Integer minAge, Integer maxAge) {
        Ad searchCriteria = new Ad();
        if (title != null && !title.isEmpty()) {
            searchCriteria.setTitle(title);
        }
        if (priceMin != null) {
            searchCriteria.setMinPrice(priceMin);
        }
        if (priceMax != null) {
            searchCriteria.setMaxPrice(priceMax);
        }
        if (city != null && !city.isEmpty()) {
            searchCriteria.setCity(city);
        }
        if (interest != null) {
            searchCriteria.setInterest(interest);
        }
        if (minAge != null) {
            searchCriteria.setMinAge(minAge);
        }
        if (maxAge != null) {
            searchCriteria.setMaxAge(maxAge);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Ad> example = Example.of(searchCriteria, matcher);

        return adsRepository.findAll(example);
    }
}
