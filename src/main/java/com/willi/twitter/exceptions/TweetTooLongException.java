package com.willi.twitter.exceptions;

public class TweetTooLongException extends RuntimeException {

    public TweetTooLongException(String message) {
        super(message);
    }
}
