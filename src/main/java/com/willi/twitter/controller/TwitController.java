package com.willi.twitter.controller;


import com.willi.twitter.controller.dto.tweet.DeleteDTO;
import com.willi.twitter.controller.dto.tweet.TweetResponseDTO;
import com.willi.twitter.controller.dto.tweet.TweetCreationDTO;
import com.willi.twitter.controller.dto.user.UserResponseDTO;
import com.willi.twitter.mappers.TweetMapper;
import com.willi.twitter.mappers.UserMapper;
import com.willi.twitter.model.TweetModel;
import com.willi.twitter.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class TwitController {

    private final TweetService tweetService;
    private final TweetMapper tweetMapper;
    private final UserMapper userMapper;

    @Autowired
    public TwitController(TweetService tweetService, TweetMapper tweetMapper, UserMapper userMapper) {
        this.tweetService = tweetService;
        this.tweetMapper = tweetMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("{userId}/tweets")
    public ResponseEntity<?> createTweet(@PathVariable Long userId, @RequestBody TweetCreationDTO request){

        TweetModel tweetToCreate = tweetMapper.toTweetModel(request);
        tweetToCreate.setOriginal(true);
        tweetService.createTwit(userId, tweetToCreate);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{userId}/tweets/{tweetId}")
    public ResponseEntity<?> getTweet(@PathVariable Long userId, @PathVariable Long tweetId){

        TweetModel tweet = tweetService.giveMeTheTweet(tweetId);

        TweetResponseDTO tweetResponse = tweetMapper.toTweetResponse(tweet);
        UserResponseDTO user = userMapper.toUserResponse(tweet.getUserOwner());

        tweetResponse.setUserOwner(user);

        return ResponseEntity.ok(tweetResponse);
    }

    @GetMapping("{userId}/tweets")
    public ResponseEntity<?> getTweets(@PathVariable Long userId){
        List<TweetModel> tweets = tweetService.getTweets(userId);
        List<TweetResponseDTO> tweetResponses = tweetMapper.toTweetResponseList(tweets);
        return ResponseEntity.ok(tweetResponses);
    }

    @DeleteMapping("{userId}/tweets")
    public ResponseEntity<?> deleteTweet(@PathVariable Long userId, @RequestBody DeleteDTO tweetTarget){
        Long tweetToDeletedId = tweetTarget.getTargetTwitId();
        tweetService.deleteTweet(userId, tweetToDeletedId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{userId}/twit/btc")
    public ResponseEntity<?> generateBTCTwit(@PathVariable Long userId){
        tweetService.generateBTCTwit(userId);
        return ResponseEntity.ok().build();
    }
 


}
