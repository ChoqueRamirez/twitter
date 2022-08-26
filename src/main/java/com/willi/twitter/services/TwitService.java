package com.willi.twitter.services;

import com.willi.twitter.business.Twit;
import com.willi.twitter.business.User;
import com.willi.twitter.dto.TwitterCreationDTO;
import com.willi.twitter.repository.TwitRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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


}
