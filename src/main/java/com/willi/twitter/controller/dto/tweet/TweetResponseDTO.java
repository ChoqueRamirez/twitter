package com.willi.twitter.controller.dto.tweet;

import com.willi.twitter.controller.dto.user.UserResponseDTO;

import java.time.LocalDateTime;

public class TweetResponseDTO {
    public long id;
    public String content;
    public LocalDateTime createdAt;
    public UserResponseDTO user;
    public boolean isOriginalTweet;

    public TweetResponseDTO(long id, String content, LocalDateTime createdAt, UserResponseDTO user, boolean isOriginalTweet) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.isOriginalTweet = isOriginalTweet;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public boolean isOriginalTweet() {
        return isOriginalTweet;
    }

    public void setOriginalTweet(boolean originalTweet) {
        isOriginalTweet = originalTweet;
    }
}
