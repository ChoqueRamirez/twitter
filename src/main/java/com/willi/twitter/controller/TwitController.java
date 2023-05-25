package com.willi.twitter.controller;


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



//    @GetMapping("/user/{userId}/twits")
//    public ResponseEntity<List<TwitResponseDTO>> getTwits(@PathVariable Long userId){
//
//        List<Twit> twits = tweetService.getTwits(userId);
//
//        List<TwitResponseDTO> twitsResponse = twits
//                .stream()
//                .map(t -> new TwitResponseDTO(
//                        t.getId(), t.getTwitOwnerUserId(), t.getContent(),
//                        t.getCreationDate().toLocalDate(), t.getAmountLikes(),
//                        t.getRetweets(),
//                        t.getUserLikes()
//                                .stream()
//                                .map(ul -> new UserLikeResponseDTO(ul.getUserLike().getId(), ul.getLikeDate().toLocalDate()))
//                                .collect(Collectors.toList())
//                ))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(twitsResponse);
//    }
//
//    @PatchMapping("/user/{userId}/twits/{twitId}/like")
//    public ResponseEntity<?> like(@PathVariable Long userId, @PathVariable Long twitId, @RequestBody UserLikeDTO userLikeTwit){
//        tweetService.like(userId, twitId, userLikeTwit);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/user/{sourceUserId}/twits/retweet")
//    public ResponseEntity<?> retweet(@PathVariable Long retweetingUserId, @RequestBody RetweetDTO targetRetweet){
//        tweetService.retweet(retweetingUserId, targetRetweet);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/twit/btc")
//    public ResponseEntity<?> generateBTCTwit(){
//        tweetService.generateBTCTwit();
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/user/{userId}/twits")
//    public ResponseEntity<?> deleteTwit(@RequestBody DeleteDTO twitToDelete, @PathVariable Long userId){
//        tweetService.deleteTwit(userId, twitToDelete);
//        return ResponseEntity.ok().build();
//    }


}
