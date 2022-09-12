package com.willi.twitter.business;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final Long id;
    private String email;
    private String password;
    private final List<Twit> twits;

//    public User (Long id){
//        this.id = id;
//
//    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.twits = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Twit> getTwits() {
        return twits;
    }

    public void twit(Twit twit) {
        twits.add(twit);
    }

    public Twit giveMeTheTwit(Long twitId) {
        return twits.stream()
                .filter(t -> t.getId().equals(twitId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
