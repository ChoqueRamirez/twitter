package com.willi.twitter.business;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwitTest {

    @Test
    public void whenLike4TimesTwitMustHave4Likes() {
        Twit twit = new Twit(
                1L,
                "Aguante Bokita"
        );
        twit.like();
        twit.like();
        twit.like();
        twit.like();
        Assertions.assertEquals(Long.valueOf(4), twit.getAmountLikes());
    }

    @Test
    public void whenCreateTwitMustHave0Likes() {
        Twit twit = new Twit(
                1L,
                "Aguante Bokita"
        );

        Assertions.assertEquals(Long.valueOf(0), twit.getAmountLikes());
    }

    @org.junit.Test(expected = RuntimeException.class)
    public void twitIsValid() {
        Twit twit = new Twit(
                1L,
                "Aguante boka Puto"
        );
    }
}