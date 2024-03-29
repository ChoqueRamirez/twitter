//package com.willi.twitter.controller;
//
//import com.willi.twitter.controller.dto.*;
//import com.willi.twitter.business.Twit;
//import com.willi.twitter.services.TwitService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//public class TwitController {
//
//    /**
//     * Recibe información y valida algunos datos
//     * */
//
//    private final TwitService twitService;
//
//    @Autowired
//    public TwitController(TwitService twitService) {
//        this.twitService = twitService;
//    }
//
//    @GetMapping("/health-check")
//    public ResponseEntity<?> healthCheck(){
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/user/{userId}/twits")
//    public ResponseEntity<?> createTwit(@RequestBody TwitterCreationDTO request, @PathVariable Long userId){
//
//        twitService.createTwit(userId, request);
//
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/user/{userId}/twits")
//    public ResponseEntity<List<TwitResponseDTO>> getTwits(@PathVariable Long userId){
//
//        List<Twit> twits = twitService.getTwits(userId);
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
//        twitService.like(userId, twitId, userLikeTwit);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/user/{sourceUserId}/twits/retweet")
//    public ResponseEntity<?> retweet(@PathVariable Long retweetingUserId, @RequestBody RetweetDTO targetRetweet){
//        twitService.retweet(retweetingUserId, targetRetweet);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/twit/btc")
//    public ResponseEntity<?> generateBTCTwit(){
//        twitService.generateBTCTwit();
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/user/{userId}/twits")
//    public ResponseEntity<?> deleteTwit(@RequestBody DeleteDTO twitToDelete, @PathVariable Long userId){
//        twitService.deleteTwit(userId, twitToDelete);
//        return ResponseEntity.ok().build();
//    }
//
//
//}
