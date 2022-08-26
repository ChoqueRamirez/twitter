package com.willi.twitter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Twit {

    private final static List<String> notValidWords = Arrays.asList(
        "puto",
        "trolo"
    );

    private String content;
    private final LocalDateTime creationDate;

    public Twit(String content) {
        final boolean isATwitWithInsult = isATwitWithInsult(content);

        if(content.length() > 140 || isATwitWithInsult){
            throw new RuntimeException("Twit muy largo o estas puteando");
        }
        this.content = content;
        this.creationDate = LocalDateTime.now();
    }

    private boolean isATwitWithInsult(String twitContent) {
        return notValidWords.stream().anyMatch(twitContent::contains);
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
