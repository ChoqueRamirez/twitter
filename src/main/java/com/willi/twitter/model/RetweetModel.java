package com.willi.twitter.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class RetweetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime creationAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "twit_id")
    private TwitModel originalTwit;

    @OneToMany(mappedBy="retweet")
    private List<LikeModel> likes;

    @OneToMany(mappedBy="retweet")
    private List<RetweetModel> retweets;


}
