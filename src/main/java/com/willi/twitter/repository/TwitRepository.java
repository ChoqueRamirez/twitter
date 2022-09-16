package com.willi.twitter.repository;

import com.willi.twitter.business.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TwitRepository {

    private final static List<User> users = Arrays.asList(
            new User(1L, "cortigeronimo@gmail.com", "1234"),
            new User(2L, "willi@gmail.com", "4321"),
            new User(3L, "fedecentu@gmail.com", "asdasd"),
            new User(4L, "camarita@gmail.com", "zxczxc")
    );

    public User getUser(Long userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
