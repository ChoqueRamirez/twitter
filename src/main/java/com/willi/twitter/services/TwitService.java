package com.willi.twitter.services;

import com.willi.twitter.business.Twit;
import com.willi.twitter.business.User;
import com.willi.twitter.business.UserReTweet;
import com.willi.twitter.dto.RetweetDTO;
import com.willi.twitter.dto.TwitterCreationDTO;
import com.willi.twitter.dto.UserLikeDTO;
import com.willi.twitter.repository.TwitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TwitService {

    /**
     * Orquesta
     * */

    private static Long twitCount = 1L;

    private final TwitRepository twitRepository;

    public TwitService(TwitRepository twitRepository) {
        this.twitRepository = twitRepository;
    }


    public void createTwit(Long userId, TwitterCreationDTO request){
        User user = twitRepository.getUser(userId);

        Twit twit = new Twit(twitCount, userId, request.getTwit());
        user.twit(twit);

        twitCount++;
    }

    public List<Twit> getTwits(Long userId){
        User user = twitRepository.getUser(userId);
        return user.getTwits();
    }

    public User getUser (Long userId){
        return twitRepository.getUser(userId);
    }

    public void like(Long userId, Long twitId, UserLikeDTO userLikeTwit){
        User user = twitRepository.getUser(userId);
        User userLike = twitRepository.getUser(userLikeTwit.getUserLikeId());

        Twit twit = user.giveMeTheTwit(twitId);

        twit.likeDislike(userLike);
    }

    public void retweet(Long sourceUserId, RetweetDTO requestRT) {
        User userSource = twitRepository.getUser(sourceUserId);

        User targetUser = twitRepository.getUser(requestRT.getTargetUserId());

        Twit twitToRetweet = targetUser.giveMeTheTwit(requestRT.getTargetTwitId());

        Optional<Twit> optionalRetweet = twitToRetweet.retweetRequest(twitCount, userSource, targetUser, twitToRetweet.getId());

        if (optionalRetweet.isPresent()){
            List<Twit> userTwits = userSource.getTwits();
            List<UserReTweet> userReTweets = twitToRetweet.getUserReTweets();

            userReTweets.add(new UserReTweet(userSource.getId()));
            userTwits.add(optionalRetweet.get());

        }else{

            Optional<Twit> twitToUnRetweet =  userSource.getTwits().stream().filter(t -> t.getContent().equals(twitToRetweet.getContent()))
                    .filter(t -> t.getCreationDate().equals(twitToRetweet.getCreationDate()))
                    .filter(t -> t.getTwitOwnerUserId().equals(twitToRetweet.getTwitOwnerUserId()))
                    .findFirst();

            List<UserReTweet> userReTweets = twitToRetweet.getUserReTweets();

            userReTweets.removeIf(u -> u.getUserRetweetId().equals(userSource.getId()));
            userSource.getTwits().removeIf(u -> u.getId().equals(twitToUnRetweet.get().getId()));
        }

        twitCount++;

    }
}
