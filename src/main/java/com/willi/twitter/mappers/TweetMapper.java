package com.willi.twitter.mappers;

import com.willi.twitter.controller.dto.tweet.TweetCreationDTO;
import com.willi.twitter.controller.dto.tweet.TweetResponseDTO;
import com.willi.twitter.model.TweetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TweetMapper {

    private final UserMapper userMapper;

    @Autowired
    public TweetMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public TweetModel toTweetModel(TweetCreationDTO tweet){
        return new TweetModel(tweet.getTwit());
    }


    public TweetResponseDTO toTweetResponse(TweetModel tweet){
        TweetResponseDTO tweetResponseDTO = new TweetResponseDTO(
                tweet.getId(), tweet.getContent(),
                tweet.getCreationDate(),
                null,
                tweet.isOriginal());
        return tweetResponseDTO;
    }


    public List<TweetResponseDTO> toTweetResponseList(List<TweetModel> tweets) {
        List<TweetResponseDTO> tweetResponses = tweets
                .stream()
                .map(t -> new TweetResponseDTO(
                        t.getId(),
                        t.getContent(),
                        t.getCreationDate(),
                        userMapper.toUserResponse(t.getUserOwner()),
                        t.isOriginal()
                ))
                .collect(Collectors.toList());

        return tweetResponses;
    }
}




