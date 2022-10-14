package com.willi.twitter.business;

import java.time.LocalDateTime;
import java.util.*;

public class Twit {

    private final static List<String> notValidWords = Arrays.asList(
        "puto",
        "trolo"
    );

    private final Long id;
    private final Long twitOwnerUserId;
    private String content;
    private final LocalDateTime creationDate;
    private List<UserLike> userLikes;
    private List<UserReTweet> userReTweets;

    public Twit(Long id, Long userId, String content) {
        final boolean isATwitWithInsult = isATwitWithInsult(content);

        if(content.length() > 140 || isATwitWithInsult){
            throw new RuntimeException("Twit muy largo o estas puteando");
        }
        this.id = id;
        this.twitOwnerUserId = userId;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.userLikes = new ArrayList<>();
        this.userReTweets = new ArrayList<>();
    }

    public Twit(Long id, Long twitOwnerUserId, String content,
                LocalDateTime creationDate, List<UserLike> userLikes, List<UserReTweet> userRetweets){
        this.id = id;
        this.twitOwnerUserId = twitOwnerUserId;
        this.content = content;
        this.creationDate = creationDate;
        this.userLikes = userLikes;
        this.userReTweets = userRetweets;
    }

    private boolean isATwitWithInsult(String twitContent) {
        return notValidWords.stream()
                .anyMatch(nvw -> twitContent.toLowerCase().contains(nvw.toLowerCase()));
    }

    public Long getId() {
        return id;
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

    public Long getRetweets(){
        return (Long) (long) userReTweets.size();
    }

    public List<UserReTweet> getUserReTweets(){
        return userReTweets;
    }

    public List<UserLike> getUserLikes() {
        return userLikes;
    }

    public Long getTwitOwnerUserId() {
        return twitOwnerUserId;
    }

    public Integer calculateLength(){
        return content.length();
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

    private void like(User userLike){
        Long userLikeId = userLike.getId();
        userLikes.add(new UserLike(userLikeId));

    }

    private void dislike(User userLike){
        userLikes.removeIf(u -> u.getUserLikeId().equals(userLike.getId()));
    }


    public Optional<Twit> retweetOrUnretweet(Long twitCount, User userSource) {

        boolean hasBeenRetweetedForTheUser = userReTweets.stream().anyMatch(tul -> tul.getUserRetweetId().equals(userSource.getId()));
        if (!hasBeenRetweetedForTheUser){
            return retweet(twitCount, userSource);
        } else {
            return unRetweet(userSource);
        }
    }

    private Optional<Twit> unRetweet(User userSource) {
        userReTweets.removeIf(ur -> Objects.equals(ur.getUserRetweetId(), userSource.getId()));
        return Optional.empty();
    }

    private Optional<Twit> retweet(Long twitCount, User userSource) {
        Twit twitRetweeted = new Twit(twitCount, twitOwnerUserId, content,
                creationDate, userLikes, userReTweets);
        userReTweets.add(new UserReTweet(userSource.getId()));
        return Optional.of(twitRetweeted);
    }


}
