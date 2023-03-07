package com.example.EventsProject.Entities;


import com.example.EventsProject.Enums.InterestsEnum;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private InterestsEnum interest;

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
