//package com.willi.twitter.services;
//
//import com.willi.twitter.business.Twit;
//import com.willi.twitter.business.User;
//import com.willi.twitter.client.BTCClient;
//import com.willi.twitter.controller.dto.DeleteDTO;
//import com.willi.twitter.controller.dto.RetweetDTO;
//import com.willi.twitter.controller.dto.TwitterCreationDTO;
//import com.willi.twitter.controller.dto.UserLikeDTO;
//import com.willi.twitter.repository.TwitRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TwitService {
//
//    /**
//     * Orquesta
//     * */
//
//    private static Long twitCount = 1L;
//
//    private final TwitRepository twitRepository;
//
//    private final BTCClient btcClient;
//    public final static long USER_ID = 5L;
//    private final static String USD = "USD";
//
//    @Autowired
//    public TwitService(TwitRepository twitRepository, BTCClient btcClient) {
//        this.twitRepository = twitRepository;
//        this.btcClient = btcClient;
//    }
//
//    public void createTwit(Long userId, TwitterCreationDTO request){
//        User user = twitRepository.getUser(userId);
//
//        Twit twit = new Twit(twitCount, userId, request.getTwit());
//        user.makeATwit(twit);
//
//        twitCount++;
//    }
//
//    public List<Twit> getTwits(Long userId){
//        User user = twitRepository.getUser(userId);
//        return user.getTwits();
//    }
//
//    public User getUser (Long userId){
//        return twitRepository.getUser(userId);
//    }
//
//    public void like(Long userId, Long twitId, UserLikeDTO userLikeTwit){
//        User user = twitRepository.getUser(userId);
//        User userLike = twitRepository.getUser(userLikeTwit.getUserLikeId());
//
//        Twit twit = user.giveMeTheTwit(twitId);
//
//        twit.likeDislike(userLike);
//    }
//
//    public void retweet(Long sourceUserId, RetweetDTO requestRT) {
//        User userSource = twitRepository.getUser(sourceUserId);
//        User targetUser = twitRepository.getUser(requestRT.getTargetUserId());
//        Twit twitToRetweet = targetUser.giveMeTheTwit(requestRT.getTargetTwitId());
//
//        Optional<Twit> optionalRetweet = twitToRetweet.retweetOrUnretweet(twitCount, userSource);
//        optionalRetweet.ifPresent(retweet -> {
//            userSource.makeATwit(retweet);
//            twitCount++;
//        });
//    }
//
//    public void generateBTCTwit(){
//        final Optional<Double> btcPriceOptional = btcClient.getBTCPrice(USD);
//        final Double btcPrice = btcPriceOptional
//                .orElseThrow(() -> new RuntimeException(String.format("No pude encontrar el precio en la moneda %s", USD)));
//        User userSource = twitRepository.getUser(USER_ID);
//        Twit twit = new Twit(
//            twitCount,
//                userSource.getId(),
//                String.format("El precio del día %s es %s", LocalDate.now(), btcPrice)
//        );
//        userSource.makeATwit(twit);
//        twitCount++;
//    }
//
//
//    public void deleteTwit(Long userId, DeleteDTO twitToDelete) {
//        User user = twitRepository.getUser(userId);
//        Twit twit = user.giveMeTheTwit(twitToDelete.getTargetTwitId());
//
//        user.deleteTwit(twit);
//
//    }
//}
