package com.willi.twitter.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Twit {

    private final static List<String> notValidWords = Arrays.asList(
        "puto",
        "trolo"
    );

    private final Long id;
    private String content;
    private final LocalDateTime creationDate;
    private Long amountLikes;
    private List<User> userLikes;

    public Twit(Long id, String content) {
        final boolean isATwitWithInsult = isATwitWithInsult(content);

        if(content.length() > 140 || isATwitWithInsult){
            throw new RuntimeException("Twit muy largo o estas puteando");
        }
        this.id = id;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.amountLikes = 0L;
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

    public Integer calculateLength(){
        return content.length();
    }

    public Long getAmountLikes() {
        return amountLikes;
    }

    public Long getId() {
        return id;
    }

    private void like(User userLike){
        this.amountLikes++;
        userLikes.add(userLike);

    }

    private void dislike(User userLike){
        this.amountLikes--;
        userLikes.remove(userLike);
    }

    public List<UserLike> getUserLikesListWithIdAndDate(){
        List<UserLike> listaModicicada = userLikes.stream()
                .map(ul -> new UserLike(ul.getId())).collect(Collectors.toList());

        return listaModicicada;
    }


    public List<Long> getUserLikesId(List<User> userLikes){

        return userLikes.stream().map(User::getId).collect(Collectors.toList());
    }

    public void likeDislike(User userLike){
        List<User> twitUserLikes = userLikes;

        boolean isAUserLikeInTheTwit = twitUserLikes.stream().anyMatch(tul -> tul.getId().equals(userLike.getId()));

        if (!isAUserLikeInTheTwit){
            like(userLike);
        }else {
            dislike(userLike);
        }

    }

}
