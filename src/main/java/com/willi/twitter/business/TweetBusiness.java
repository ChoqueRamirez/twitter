package com.willi.twitter.business;

import com.willi.twitter.exceptions.TweetTooLongException;
import com.willi.twitter.model.TweetModel;
import com.willi.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetBusiness {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetBusiness(TweetRepository tweetRepository){
        this.tweetRepository = tweetRepository;
    }

    public void validateTweetLength(TweetModel tweet){
        if (tweet.getContent().length() > 280){
            throw new TweetTooLongException("El tweet esta excediendo la cantidad maxima de caracteres");
        }
    }

}

