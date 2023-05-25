//package com.willi.twitter.business;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//public class Twit {
//
//    private final static List<String> notValidWords = Arrays.asList(
//        "puto",
//        "trolo"
//    );
//
//    private final Long id;
//    private final Long twitOwnerUserId;
//    private String content;
//    private final LocalDateTime creationDate;
//    private final List<UserLike> userLikes;
//    private final List<User> userReTweets;
//
//    private final Twit originalTwit;
//
//    public Twit(Long id, Long userId, String content) {
//        final boolean isATwitWithInsult = isATwitWithInsult(content);
//
//        if(content.length() > 140 || isATwitWithInsult){
//            throw new RuntimeException("Twit muy largo o estas puteando");
//        }
//        this.id = id;
//        this.twitOwnerUserId = userId;
//        this.content = content;
//        this.creationDate = LocalDateTime.now();
//        this.userLikes = new ArrayList<>();
//        this.userReTweets = new ArrayList<>();
//        this.originalTwit = null;
//    }
//
//    public Twit(Long id, Long twitOwnerUserId, String content,
//                LocalDateTime creationDate, List<UserLike> userLikes, List<User> userRetweets, Twit originalTwit){
//        this.id = id;
//        this.twitOwnerUserId = twitOwnerUserId;
//        this.content = content;
//        this.creationDate = creationDate;
//        this.userLikes = userLikes;
//        this.userReTweets = userRetweets;
//        this.originalTwit = originalTwit;
//    }
//
//    private boolean isATwitWithInsult(String twitContent) {
//        return notValidWords.stream()
//                .anyMatch(nvw -> twitContent.toLowerCase().contains(nvw.toLowerCase()));
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//
//    public Long getAmountLikes() {
//        return (Long) (long) userLikes.size();
//    }
//
//    public Long getRetweets(){
//        return (Long) (long) userReTweets.size();
//    }
//
//    public List<User> getUserReTweets(){
//        return userReTweets;
//    }
//
//    public List<UserLike> getUserLikes() {
//        return userLikes;
//    }
//
//    public Long getTwitOwnerUserId() {
//        return twitOwnerUserId;
//    }
//
//    public Twit getOriginalTwit() {
//        return originalTwit;
//    }
//
//    public void likeDislike(User user){
//
//        boolean isAUserLikeInTheTwit = userLikes.stream().anyMatch(ul -> ul.getUserLike().equals(user));
//
//        if (!isAUserLikeInTheTwit){
//            like(user);
//        }else {
//            dislike(user);
//        }
//    }
//
//    private void like(User userLike){
//        userLikes.add(new UserLike(userLike));
//    }
//
//    private void dislike(User userLike){
//        userLikes.removeIf(u -> u.getUserLike().equals(userLike));
//    }
//
//
//    public Optional<Twit> retweetOrUnretweet(Long twitCount, User userSource) {
//
//        boolean hasBeenRetweetedForTheUser = userReTweets.stream().anyMatch(tul -> tul.getId().equals(userSource.getId()));
//        if (!hasBeenRetweetedForTheUser){
//            return retweet(twitCount, userSource);
//        } else {
//            return unRetweet(userSource);
//        }
//    }
//
//    private Optional<Twit> unRetweet(User userSource) {
//        userReTweets.removeIf(ur -> Objects.equals(ur.getId(), userSource.getId()));
//        if(this.originalTwit != null){
//            // Si no es el original
//            userSource.getTwits().removeIf(t -> Objects.equals(t.originalTwit.id, id));
//        } else {
//            // Si es el original
//            userSource.getTwits().removeIf(ut -> Objects.nonNull(ut.originalTwit)
//                    && Objects.equals(ut.originalTwit.id, id));
//        }
//        return Optional.empty();
//    }
//
//    private Optional<Twit> retweet(Long twitCount, User userSource) {
//        Twit twitRetweeted = new Twit(twitCount, twitOwnerUserId, content,
//                creationDate, userLikes, userReTweets, this);
//        userReTweets.add(userSource);
//        return Optional.of(twitRetweeted);
//    }
//
//
//}
