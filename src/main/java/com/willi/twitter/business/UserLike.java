package com.willi.twitter.business;

import java.time.LocalDateTime;

public class UserLike {
    private User userLike;
    private final LocalDateTime likeDate;

    public UserLike(User userLike) {
        this.likeDate = LocalDateTime.now();
        this.userLike = userLike;
    }

    public LocalDateTime getLikeDate() {
        return likeDate;
    }

    public User getUserLike() {
        return userLike;
    }

    public void setUserLike(User userLike) {
        this.userLike = userLike;
    }
}
