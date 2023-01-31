package com.willi.twitter.model;

import javax.persistence.*;

public class RetweetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "twit_id")
    private TwitModel twit;

    @ManyToOne
    @JoinColumn(name = "original_twit_id")
    private TwitModel originalTwit;
}
