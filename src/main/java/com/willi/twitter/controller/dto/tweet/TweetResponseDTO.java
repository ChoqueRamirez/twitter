package com.willi.twitter.controller.dto.tweet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willi.twitter.controller.dto.user.UserResponseDTO;
import java.time.LocalDateTime;

public class TweetResponseDTO {
    private long id;
    private String content;
    private LocalDateTime creationDate;
    private UserResponseDTO userOwner;
    private boolean isOriginal;

    public TweetResponseDTO(long id, String content, LocalDateTime creationDate, UserResponseDTO userOwner, boolean isOriginal) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.userOwner = userOwner;
        this.isOriginal = isOriginal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UserResponseDTO getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserResponseDTO userOwner) {
        this.userOwner = userOwner;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }
}
