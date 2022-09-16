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

        Twit twit = new Twit(twitCount, request.getTwit());
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

    public void retweet(Long sourceUserId, RetweetDTO targetRetweet) {
        //Traigo al usuario que esta dando el rt
        User userSource = twitRepository.getUser(sourceUserId);

        //Traigo al suuario que tiene el twit a rt
        User targetUser = twitRepository.getUser(targetRetweet.getTargetUserId());
        //consigo el twit a rt
        Twit twitToRetweet = targetUser.giveMeTheTwit(targetRetweet.getTargetTwitId());

        Twit reTwit = new Twit(twitToRetweet.getId(), twitToRetweet.getContent());

        List<Twit> userTwits = userSource.getTwits();
        userTwits.add(reTwit);
    }
}
