package com.willi.twitter.services;

import com.willi.twitter.business.TweetBusiness;
import com.willi.twitter.business.Twit;
import com.willi.twitter.business.User;
import com.willi.twitter.client.BTCClient;
import com.willi.twitter.controller.dto.tweet.DeleteDTO;
import com.willi.twitter.controller.dto.tweet.RetweetDTO;
import com.willi.twitter.controller.dto.user.UserLikeDTO;
import com.willi.twitter.model.TweetModel;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.TweetRepository;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final TweetBusiness tweetBusiness;


    private final BTCClient btcClient;
    public final static long USER_ID = 5L;
    private final static String USD = "USD";

    @Autowired
    public TweetService(TweetRepository tweetRepository, UserRepository userRepository, TweetBusiness tweetBusiness, BTCClient btcClient) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.tweetBusiness = tweetBusiness;
        this.btcClient = btcClient;
    }

    public void createTwit(Long userId, TweetModel tweetToCreate){
        Optional<UserModel> user = userRepository.findById(userId);
        if (user.isPresent()){
            tweetToCreate.setUser(user.get());
            tweetRepository.save(tweetToCreate);
        }

    }

//    public List<Twit> getTwits(Long userId){
//        User user = tweetRepository.getUser(userId);
//        return user.getTwits();
//    }
//
//    public User getUser (Long userId){
//        return tweetRepository.getUser(userId);
//    }
//
//    public void like(Long userId, Long twitId, UserLikeDTO userLikeTwit){
//        User user = tweetRepository.getUser(userId);
//        User userLike = tweetRepository.getUser(userLikeTwit.getUserLikeId());
//
//        Twit twit = user.giveMeTheTwit(twitId);
//
//        twit.likeDislike(userLike);
//    }
//
//    public void retweet(Long sourceUserId, RetweetDTO requestRT) {
//        User userSource = tweetRepository.getUser(sourceUserId);
//        User targetUser = tweetRepository.getUser(requestRT.getTargetUserId());
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
//        User userSource = tweetRepository.getUser(USER_ID);
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
//        User user = tweetRepository.getUser(userId);
//        Twit twit = user.giveMeTheTwit(twitToDelete.getTargetTwitId());
//
//        user.deleteTwit(twit);
//
//    }
}
