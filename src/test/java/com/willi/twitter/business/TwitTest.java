package com.willi.twitter.business;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

public class TwitTest {

    @Test
    public void whenLike4TimesTwitMustHave4Likes() {
        Twit twit = new Twit(
                1L, 1L, "Aguante Bokita"
        );
        User gerito = new User(
                1L,
                "gerito@gmail.com",
                "1234"
        );
        User williwonkis = new User(
                2L,
                "willi@gmail.com",
                "2345"
        );
        User centu = new User(
                3L,
                "centu@gmail.com",
                "23456"
        );
        User camarin = new User(
                4L,
                "camarita@gmail.com",
                "dadsad2"
        );
        twit.likeDislike(centu);
        twit.likeDislike(williwonkis);
        twit.likeDislike(gerito);
        twit.likeDislike(camarin);
        Assertions.assertEquals(Long.valueOf(4), twit.getAmountLikes());
    }

    @Test
    public void whenLike3TimesWithSameUserTwitMustHave1Like() {
        Twit twit = new Twit(
                1L,
                1L, "Aguante Bokita"
        );
        User gerito = new User(
                1L,
                "gerito@gmail.com",
                "1234"
        );
        User williwonkis = new User(
                2L,
                "willi@gmail.com",
                "2345"
        );
        twit.likeDislike(williwonkis);
        twit.likeDislike(gerito);
        twit.likeDislike(gerito);
        Assertions.assertEquals(Long.valueOf(1), twit.getAmountLikes());
    }

    @Test
    public void whenCreateTwitMustHave0Likes() {
        Twit twit = new Twit(
                1L, 1L, "Aguante Bokita"
        );

        Assertions.assertEquals(Long.valueOf(0), twit.getAmountLikes());
    }

    @Test(expected = RuntimeException.class)
    public void twitIsValid() {
        Twit twit = new Twit(
                1L, 1L, "Aguante boka Puto"
        );
    }

    @Test
    public void whenRetweets3TimesTwitMustHave3Retweet(){
        Twit twit = new Twit(
                1L, 1L, "Aguante Bokita"
        );
        User gerito = new User(
                1L,
                "gerito@gmail.com",
                "1234"
        );
        User centu = new User(
                3L,
                "centu@gmail.com",
                "23456"
        );
        User camarin = new User(
                4L,
                "camarita@gmail.com",
                "dadsad2"
        );
        twit.retweetOrUnretweet(1L, centu);
        twit.retweetOrUnretweet(2L, gerito);
        twit.retweetOrUnretweet(3L, camarin);
        Assertions.assertEquals(Long.valueOf(3), twit.getRetweets());
    }

    @Test
    public void whenCreateTwitMustHave0Retweet() {
        Twit twit = new Twit(
                1L, 1L, "Aguante Bokita"
        );

        Assertions.assertEquals(Long.valueOf(0), twit.getRetweets());
    }

    @Test
    public void whenRetweet3TimesWithSameUserTwitMustHave1Retweet() {
        Twit twit = new Twit(
                1L,
                1L, "Aguante Bokita"
        );
        User gerito = new User(
                1L,
                "gerito@gmail.com",
                "1234"
        );
        User williwonkis = new User(
                2L,
                "willi@gmail.com",
                "2345"
        );
        twit.retweetOrUnretweet(1L, williwonkis);
        twit.retweetOrUnretweet(2L, williwonkis);
        twit.retweetOrUnretweet(3L, williwonkis);
        Assertions.assertEquals(Long.valueOf(1), twit.getRetweets());
    }

    @Test
    public void whenRetweenARetweet(){
        Twit twit = new Twit(
                1L,
                1L, "Aguante Bokita"
        );
        User gerito = new User(
                1L,
                "gerito@gmail.com",
                "1234"
        );
        gerito.twit(twit);
        User williwonkis = new User(
                2L,
                "willi@gmail.com",
                "2345"
        );
        Optional<Twit> retweetOptional = twit.retweetOrUnretweet(1L, williwonkis);
        Twit retweet = retweetOptional.get();
        williwonkis.twit(retweet);
        User centu = new User(
                3L,
                "centu@gmail.com",
                "23456"
        );
        Optional<Twit> retweetOfRetweet = retweet.retweetOrUnretweet(2L, centu);
        centu.twit(retweetOfRetweet.get());

        Assertions.assertEquals(2, twit.getRetweets());

    }
}