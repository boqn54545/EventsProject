package com.example.EventsProject.Entities;


import javax.persistence.*;
import java.sql.Date;

@Entity
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    @Column(name = "user_id")
     private  User user;

     private String title;

     private Integer price;

     private String city;

     private String description;

     private Integer minAge;

     private Integer maxAge;

     private Date createdAt;

     private Date expireAt;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
