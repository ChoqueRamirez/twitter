package com.willi.twitter.business;

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
}

