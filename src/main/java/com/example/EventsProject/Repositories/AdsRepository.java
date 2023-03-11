package com.example.EventsProject.Repositories;

import com.example.EventsProject.Entities.Ad;
import com.example.EventsProject.Entities.User;
import com.example.EventsProject.Enums.InterestsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ad,Long> {
    @Query("SELECT a FROM Ad a WHERE " +
            "(:title IS NULL OR a.title LIKE %:title%) " +
            "AND (:interest IS NULL OR a.interest = :interest) " +
            "AND (:priceMin IS NULL OR a.minPrice >= :priceMin) " +
            "AND (:priceMax IS NULL OR a.maxPrice <= :priceMax) " +
            "AND (:city IS NULL OR a.city = :city) " +
            "AND (:minAge IS NULL OR a.minAge >= :minAge) " +
            "AND (:maxAge IS NULL OR a.maxAge <= :maxAge)")

    List<Ad> customSearch(String title, InterestsEnum interest, Integer priceMin, Integer priceMax, String city, Integer minAge, Integer maxAge);

}
