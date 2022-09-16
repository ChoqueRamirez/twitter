package com.willi.twitter.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Twit {

    private final static List<String> notValidWords = Arrays.asList(
        "puto",
        "trolo"
    );

    private final Long id;
    private Long originalTwitId;
    private String content;
    private final LocalDateTime creationDate;
    private List<UserLike> userLikes;
    private Long retweets;

    public Twit(Long id, String content) {
        final boolean isATwitWithInsult = isATwitWithInsult(content);

        if(content.length() > 140 || isATwitWithInsult){
            throw new RuntimeException("Twit muy largo o estas puteando");
        }
        this.id = id;
        this.originalTwitId = 0L;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.userLikes = new ArrayList<>();
        this.retweets = 0L;
    }

    private boolean isATwitWithInsult(String twitContent) {
        return notValidWords.stream()
                .anyMatch(nvw -> twitContent.toLowerCase().contains(nvw.toLowerCase()));
    }


    public Long getOriginalTwitId(){
        return originalTwitId;
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


    public Long getAmountLikes() {
        return (Long) (long) userLikes.size();
    }

    public Long getId() {
        return id;
    }

    public List<UserLike> getUserLikes() {
        return userLikes;
    }

    public Long getRetweets() {
        return retweets;
    }

    public void setRetweets(Long retweets) {
        this.retweets = retweets;
    }

    public Integer calculateLength(){
        return content.length();
    }

    private void like(User userLike){
        Long userLikeId = userLike.getId();
        userLikes.add(new UserLike(userLikeId));

    }

    private void dislike(User userLike){
        userLikes.removeIf(u -> u.getUserLikeId().equals(userLike.getId()));
    }


    public void likeDislike(User user){
        List<UserLike> twitUserLikes = userLikes;

        boolean isAUserLikeInTheTwit = twitUserLikes.stream().anyMatch(tul -> tul.getUserLikeId().equals(user.getId()));

        if (!isAUserLikeInTheTwit){
            like(user);
        }else {
            dislike(user);
        }
    }

    public void retweet(User userSource, Twit twitToRetweet) {
        twitToRetweet.retweets++;
        twitToRetweet.originalTwitId++;
        List<Twit> userTwits = userSource.getTwits();
        userTwits.add(twitToRetweet);
    }
}
