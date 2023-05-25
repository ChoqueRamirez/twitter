package com.willi.twitter.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tweet")
public class TweetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_owner_id", nullable = false)
    private UserModel userOwner;

    private boolean isOriginal;

    public TweetModel(Long id, String content, LocalDateTime creationDate, UserModel userOwner, boolean isOriginal) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.userOwner = userOwner;
        this.isOriginal = isOriginal;
    }

    public TweetModel(String content){
        this.content = content;
    }

    public TweetModel(){};

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

    public UserModel getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserModel userOwner) {
        this.userOwner = userOwner;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

}
