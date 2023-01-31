package com.willi.twitter.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class TwitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String content;

    public LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @OneToMany(mappedBy = "twit", cascade = CascadeType.ALL)
    private List<TwitModel> likes;

    @OneToMany(mappedBy = "twit", cascade = CascadeType.ALL)
    private List<RetweetModel> retweets;

    private boolean isOriginal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<TwitModel> getLikes() {
        return likes;
    }

    public void setLikes(List<TwitModel> likes) {
        this.likes = likes;
    }

    public List<RetweetModel> getRetweets() {
        return retweets;
    }

    public void setRetweets(List<RetweetModel> retweets) {
        this.retweets = retweets;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean original) {
        isOriginal = original;
    }

}
