package com.willi.twitter.dto;

import java.time.LocalDate;
import java.util.List;

public class TwitResponseDTO {

    public Long id;
    public Long originalTwitId;
    public String content;
    public LocalDate creationDate;
    public Long amountLikes;
    public List<UserLikeResponseDTO> userLikesResponse;
    public Long retweets;


    public TwitResponseDTO(Long id, Long originalTwitId, String content, LocalDate creationDate, Long amountLikes, Long retweets, List<UserLikeResponseDTO> userLikesResponse) {
        this.id = id;
        this.originalTwitId = originalTwitId;
        this.content = content;
        this.creationDate = creationDate;
        this.amountLikes = amountLikes;
        this.retweets = retweets;
        this.userLikesResponse = userLikesResponse;
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

    public List<UserLikeResponseDTO> getUserLikesResponse() {
        return userLikesResponse;
    }

    public Long getOriginalTwitId() {
        return originalTwitId;
    }

}
