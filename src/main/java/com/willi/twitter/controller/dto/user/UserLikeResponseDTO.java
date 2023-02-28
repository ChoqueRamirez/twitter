package com.willi.twitter.controller.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserLikeResponseDTO {
    private Long userLikeId;
    private LocalDate likeDate;

    public UserLikeResponseDTO(Long userLikeId, LocalDate likeDate) {
        this.userLikeId = userLikeId;
        this.likeDate = likeDate;
    }

    public Long getUserLikeId() {
        return userLikeId;
    }

    public void setUserLikeId(Long userLikeId) {
        this.userLikeId = userLikeId;
    }

    public LocalDate getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(LocalDate likeDate) {
        this.likeDate = likeDate;
    }
}
