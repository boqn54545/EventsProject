package com.example.EventsProject.Repositories;

import com.example.EventsProject.Entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ad,Long> {
}
