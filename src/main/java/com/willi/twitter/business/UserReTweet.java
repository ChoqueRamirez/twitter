package com.willi.twitter.business;

public class UserReTweet {

    private Long userRetweetId;
    private Twit retweet;

    public UserReTweet(Long userRetweetId) {
        this.userRetweetId = userRetweetId;
    }


    public Long getUserRetweetId() {
        return userRetweetId;
    }

    public void setUserRetweetId(Long userRetweetId) {
        this.userRetweetId = userRetweetId;
    }

    public Twit getRetweet() {
        return retweet;
    }

    public void setRetweet(Twit retweet) {
        this.retweet = retweet;
    }
}
