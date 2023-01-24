package com.willi.twitter.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private final Long id;
    private String email;
    private String password;
    private final List<Twit> twits;

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

    public void makeATwit(Twit twit) {
        twits.add(twit);
    }

    public Twit giveMeTheTwit(Long twitId) {
        return twits.stream()
                .filter(t -> t.getId().equals(twitId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public void deleteTwit(Twit twitToDelete) {
        twits.removeIf(t -> Objects.equals(t.getId(), twitToDelete.getId()));
    }
}
