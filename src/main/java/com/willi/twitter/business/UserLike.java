package com.willi.twitter.business;

import java.time.LocalDateTime;

public class UserLike {
    private Long userLikeId;
    private final LocalDateTime likeDate;

    public UserLike(Long userLikeId) {
        this.userLikeId = userLikeId;
        this.likeDate = LocalDateTime.now();
    }

    public Long getUserLikeId() {
        return userLikeId;
    }

    public void setUserLikeId(Long userLikeId) {
        this.userLikeId = userLikeId;
    }

    public LocalDateTime getLikeDate() {
        return likeDate;
    }
}
