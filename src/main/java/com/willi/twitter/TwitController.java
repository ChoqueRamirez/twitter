package com.willi.twitter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TwitController {

    private final static List<User> users = Arrays.asList(
            new User(1L, "cortigeronimo@gmail.com", "1234"),
            new User(2L, "willi@gmail.com", "4321")
    );

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{userId}/twits")
    public ResponseEntity<?> createTwit(@RequestBody TwitterCreationDTO request, @PathVariable Long userId){
        Optional<User> optionalUser = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        User user = optionalUser.orElseThrow(RuntimeException::new);

        Twit twit = new Twit(request.getTwit());

        user.twit(twit);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/twits")
    public ResponseEntity<List<TwitResponseDTO>> getTwits(@PathVariable Long userId){
        Optional<User> optionalUser = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        User user = optionalUser.orElseThrow(RuntimeException::new);

        List<Twit> twits = user.getTwits();

        List<TwitResponseDTO> twitsResponse = twits.stream()
                .map(t -> new TwitResponseDTO(t.getContent(), t.getCreationDate().toLocalDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(twitsResponse);
    }


}
