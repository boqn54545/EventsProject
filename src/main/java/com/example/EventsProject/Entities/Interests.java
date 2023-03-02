package com.example.EventsProject.Entities;

import com.example.EventsProject.Enums.Interest;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;


@Entity
public class Interests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    @Column(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Interest interest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
