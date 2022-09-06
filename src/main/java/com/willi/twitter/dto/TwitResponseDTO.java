package com.willi.twitter.dto;

import java.time.LocalDate;

public class TwitResponseDTO {

    public Long id;
    public String content;
    public LocalDate creationDate;
    public Long likes;

    public TwitResponseDTO(Long id, String content, LocalDate creationDate, Long likes) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.likes = likes;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
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
}
