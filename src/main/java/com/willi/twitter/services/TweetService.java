package com.willi.twitter.services;

import com.willi.twitter.business.TweetBusiness;
import com.willi.twitter.client.BTCClient;
import com.willi.twitter.exceptions.UnauthorizedAccessException;
import com.willi.twitter.model.TweetModel;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.TweetRepository;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final TweetBusiness tweetBusiness;
    private final IUserService userService;


    private final BTCClient btcClient;
    private final static String USD = "USD";

    @Autowired
    public TweetService(TweetRepository tweetRepository, UserRepository userRepository, TweetBusiness tweetBusiness, IUserService userService, BTCClient btcClient) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.tweetBusiness = tweetBusiness;
        this.userService = userService;
        this.btcClient = btcClient;
    }

    public void createTwit(Long userId, TweetModel tweetToCreate){
        Optional<UserModel> user = userRepository.findById(userId);

        tweetBusiness.validateTweetLength(tweetToCreate);

        if (user.isPresent()){
            tweetToCreate.setUserOwner(user.get());
            tweetToCreate.setCreationDate(LocalDateTime.now());
            tweetRepository.save(tweetToCreate);
        }
    }

    public TweetModel giveMeTheTweet(Long tweetId) {
        Optional<TweetModel> tweet = tweetRepository.findById(tweetId);
        return tweet.orElse(null);
    }

    public List<TweetModel> getTweets(Long userId) {
        return tweetRepository.findByUserOwnerId(userId);
    }

    public void deleteTweet(Long userId, Long tweetToDeletedId){
        Optional<UserModel> user = userRepository.findById(userId);
        Optional<TweetModel> tweetToDelete = tweetRepository.findById(tweetToDeletedId);

        if (user.isPresent() && tweetToDelete.isPresent()){
            if (Objects.equals(user.get().getId(), tweetToDelete.get().getUserOwner().getId())){
                tweetRepository.deleteById(tweetToDeletedId);
            }else {
                throw new UnauthorizedAccessException("Se esta tratando de eliminar el tweet de otro usuario");
            }
        }

    }


    public void generateBTCTwit(Long userId){
        final Optional<Double> btcPriceOptional = btcClient.getBTCPrice(USD);
        final Double btcPrice = btcPriceOptional
                .orElseThrow(() -> new RuntimeException(String.format("No pude encontrar el precio en la moneda %s", USD)));
        Optional<UserModel> userSource = userService.getUserById((userId));

        if (userSource.isPresent()){
            TweetModel tweetBtc = new TweetModel(
                    userSource.get().getId(),
                    String.format("El precio del dia %s es %s", LocalDate.now(), btcPrice),
                    LocalDateTime.now(),
                    userSource.get(),
                    true
                    );
            tweetRepository.save(tweetBtc);
        }




    }

}
