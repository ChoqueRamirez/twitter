package com.willi.twitter.dto;

import com.willi.twitter.business.User;
import com.willi.twitter.business.UserLike;

import java.time.LocalDate;
import java.util.List;

public class TwitResponseDTO {

    public Long id;
    public String content;
    public LocalDate creationDate;
    public Long amountLikes;
    public List<UserLike> userLikes;


    public TwitResponseDTO(Long id, String content, LocalDate creationDate, Long amountLikes, List<UserLike> userLikes) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.amountLikes = amountLikes;
        this.userLikes = userLikes;
    }

    public Long getAmountLikes() {
        return amountLikes;
    }

    public void setAmountLikes(Long amountLikes) {
        this.amountLikes = amountLikes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<UserLike> getUserLikes() {
        return userLikes;
    }

}
