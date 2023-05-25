package com.willi.twitter.mappers;

import com.willi.twitter.controller.dto.tweet.TweetCreationDTO;
import com.willi.twitter.controller.dto.tweet.TweetResponseDTO;
import com.willi.twitter.model.TweetModel;
import org.springframework.stereotype.Component;


@Component
public class TweetMapper {

    public TweetModel toTweetModel(TweetCreationDTO tweet){
        return new TweetModel(tweet.getTwit());
    }


    public TweetResponseDTO toTweetResponse(TweetModel tweet){
        TweetResponseDTO tweetResponseDTO = new TweetResponseDTO(tweet.getId(), tweet.getContent(),
                                                                tweet.getCreationDate(),
                                                                null,
                                                                tweet.isOriginal());

        return tweetResponseDTO;
    }



}




