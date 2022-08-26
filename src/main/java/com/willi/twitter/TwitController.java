package com.willi.twitter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TwitController {

    private final static List<Twit> twits = new ArrayList<>();

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/twits")
    public ResponseEntity<?> createTwit(@RequestBody TwitterCreationDTO request){
        Twit twit = new Twit(request.getTwit());
        twits.add(twit);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/twits")
    public ResponseEntity<List<TwitResponseDTO>> getTwits(){
        List<TwitResponseDTO> stream = twits.stream()
                .map(t -> new TwitResponseDTO(t.getContent(), t.getCreationDate().toLocalDate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(stream);
    }


}
