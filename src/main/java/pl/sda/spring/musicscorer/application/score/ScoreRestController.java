package pl.sda.spring.musicscorer.application.score;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreRestController {

    @PostMapping("/scores")
    public ResponseEntity<Void> score(@RequestBody ScoreRequest scoreRequest){
        return null;
    }
}
