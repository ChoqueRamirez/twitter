package com.willi.twitter.services;

import com.willi.twitter.business.Twit;
import com.willi.twitter.business.User;
import com.willi.twitter.dto.TargetTwitDTO;
import com.willi.twitter.dto.RetweetDTO;
import com.willi.twitter.dto.TwitterCreationDTO;
import com.willi.twitter.dto.UserLikeDTO;
import com.willi.twitter.repository.TwitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void like(Long userId, Long twitId, UserLikeDTO userLikeTwit){
        User user = twitRepository.getUser(userId);
        User userLike = twitRepository.getUser(userLikeTwit.getUserLikeId());

        Twit twit = user.giveMeTheTwit(twitId);

        twit.likeDislike(userLike);
    }

    public User getUser (Long userId){
        return twitRepository.getUser(userId);
    }

    public void retweet(Long sourceUserId, RetweetDTO requestRT) {
        User userSource = twitRepository.getUser(sourceUserId);

        User targetUser = twitRepository.getUser(requestRT.getTargetUserId());

        Twit twitToRetweet = targetUser.giveMeTheTwit(requestRT.getTargetTwitId());

        twitToRetweet.retweetRequest(userSource, twitToRetweet);

    }
}
