package com.willi.twitter.dto;

import java.time.LocalDate;
import java.util.List;

public class TwitResponseDTO {

    public Long id;
    public Long twitOwnerUserId;
    public String content;
    public LocalDate creationDate;
    public Long amountLikes;
    public Long retweets;
    public List<UserLikeResponseDTO> userLikesResponse;


    public TwitResponseDTO(Long id, Long twitOwnerUserId, String content,
                           LocalDate creationDate, Long amountLikes, Long retweets,
                           List<UserLikeResponseDTO> userLikesResponse) {
        this.id = id;
        this.twitOwnerUserId = twitOwnerUserId;
        this.content = content;
        this.creationDate = creationDate;
        this.amountLikes = amountLikes;
        this.retweets = retweets;
        this.userLikesResponse = userLikesResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTwitOwnerUserId() {
        return twitOwnerUserId;
    }

    public void setTwitOwnerUserId(Long twitOwnerUserId) {
        this.twitOwnerUserId = twitOwnerUserId;
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

    public Long getRetweets() {
        return retweets;
    }

    public void setRetweets(Long retweets) {
        this.retweets = retweets;
    }

    public List<UserLikeResponseDTO> getUserLikesResponse() {
        return userLikesResponse;
    }


}
