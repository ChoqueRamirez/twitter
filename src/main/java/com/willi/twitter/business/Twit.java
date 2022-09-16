package com.willi.twitter.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Twit {

    private final static List<String> notValidWords = Arrays.asList(
        "puto",
        "trolo"
    );

    private final Long id;
    private String content;
    private final LocalDateTime creationDate;
    //private Long amountLikes;
    private List<UserLike> userLikes;

    public Twit(Long id, String content) {
        final boolean isATwitWithInsult = isATwitWithInsult(content);

        if(content.length() > 140 || isATwitWithInsult){
            throw new RuntimeException("Twit muy largo o estas puteando");
        }
        this.id = id;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        //this.amountLikes = 0L;
        this.userLikes = new ArrayList<>();
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


    public Long getAmountLikes() {
        return (Long) (long) userLikes.size();
    }

    public Long getId() {
        return id;
    }

    public List<UserLike> getUserLikes() {
        return userLikes;
    }

    public Integer calculateLength(){
        return content.length();
    }

    private void like(User userLike){
        //this.amountLikes++;
        Long userLikeId = userLike.getId();
        userLikes.add(new UserLike(userLikeId));

    }

    private void dislike(User userLike){
        //this.amountLikes--;
        UserLike userRemove = userLikes.stream()
                .filter(ul -> ul.getUserLikeId().equals(userLike.getId()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        userLikes.remove(userRemove);
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


}
