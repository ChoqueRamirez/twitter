package com.willi.twitter.controller;

import com.willi.twitter.business.Twit;
import com.willi.twitter.dto.TwitResponseDTO;
import com.willi.twitter.dto.TwitterCreationDTO;
import com.willi.twitter.business.User;
import com.willi.twitter.services.TwitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TwitController {

    /**
     * Recibe información y valida algunos datos
     * */


    private final static List<User> users = Arrays.asList(
            new User(1L, "cortigeronimo@gmail.com", "1234"),
            new User(2L, "willi@gmail.com", "4321")
    );

    private final TwitService twitService;

    @Autowired
    public TwitController(TwitService twitService) {
        this.twitService = twitService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/twits")
    public ResponseEntity<?> createTwit(@RequestBody TwitterCreationDTO request, @PathVariable Long userId){

        twitService.createTwit(userId, request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/twits")
    public ResponseEntity<List<TwitResponseDTO>> getTwits(@PathVariable Long userId){
        User user = getUser(userId);

        List<Twit> twits = user.getTwits();

        List<TwitResponseDTO> twitsResponse = twits.stream()
                .map(t -> new TwitResponseDTO(t.getId(), t.getContent(), t.getCreationDate().toLocalDate(), t.getLikes()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(twitsResponse);
    }

    @PatchMapping("/user/{userId}/twits/{twitId}/like")
    public ResponseEntity<?> like(@PathVariable Long userId, @PathVariable Long twitId){
        User user = getUser(userId);
        Twit twit = user.giveMeTheTwit(twitId);
        twit.like();
        return ResponseEntity.ok().build();
    }

    private User getUser(Long userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }


}
