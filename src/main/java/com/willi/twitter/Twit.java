package com.willi.twitter;

import java.time.LocalDateTime;

public class Twit {
    private String content;
    private final LocalDateTime creationDate;

    public Twit(String content) {
        this.content = content;
        this.creationDate = LocalDateTime.now();
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

    public Integer calculateLength(){
        return content.length();
    }
}
