package com.willi.twitter.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserLikeResponseDTO {
    private Long userLikeId;
    private LocalDateTime likeDate;

    public UserLikeResponseDTO(Long userLikeId, LocalDateTime likeDate) {
        this.userLikeId = userLikeId;
        this.likeDate = likeDate;
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

    public void setLikeDate(LocalDateTime likeDate) {
        this.likeDate = likeDate;
    }
}
