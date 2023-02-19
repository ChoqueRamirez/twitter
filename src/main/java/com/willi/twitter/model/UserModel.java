package com.willi.twitter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TwitModel> twits;


    public UserModel(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserModel(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.twits = new ArrayList<>();
    }

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TwitModel> getTwits() {
        return twits;
    }

    public void setTwits(List<TwitModel> twits) {
        this.twits = twits;
    }
}
