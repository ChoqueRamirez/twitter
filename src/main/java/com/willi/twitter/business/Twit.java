package com.willi.twitter.business;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Twit {

    private final static List<String> notValidWords = Arrays.asList(
        "puto",
        "trolo"
    );

    private final Long id;
    private String content;
    private final LocalDateTime creationDate;
    private Long likes;

    public Twit(Long id, String content) {
        final boolean isATwitWithInsult = isATwitWithInsult(content);

        if(content.length() > 140 || isATwitWithInsult){
            throw new RuntimeException("Twit muy largo o estas puteando");
        }
        this.id = id;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.likes = 0L;
    }

    private boolean isATwitWithInsult(String twitContent) {
        return notValidWords.stream()
                .anyMatch(nvw -> twitContent.toLowerCase().contains(nvw.toLowerCase()));
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

    public Long getLikes() {
        return likes;
    }

    public void like(){
        this.likes++;
    }

    public Long getId() {
        return id;
    }
}
