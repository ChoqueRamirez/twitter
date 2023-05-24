package com.willi.twitter.mappers;

import com.willi.twitter.controller.dto.tweet.TweetCreationDTO;
import com.willi.twitter.controller.dto.tweet.TweetResponseDTO;
import com.willi.twitter.model.TweetModel;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TweetMapper {

    public TweetModel toTweetModel(TweetCreationDTO tweet){
        return new TweetModel(
                tweet.getTwit(),
                LocalDateTime.now(),
                true);
    }

    /**
    public TweetResponseDTO toTweetResponse(TweetModel tweet){
        return new TweetResponseDTO(
                tweet.getId(),
                tweet.getContent(),
                tweet.getCreationDate(),
                tweet.getUser(),
                tweet.isOriginal()

                );
        }
    }
**/

}
