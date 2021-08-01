package pl.sda.spring.musicscorer.application.score;

import lombok.Value;

@Value
public class ScoreRequest {
    String scorer;
    int score;
    String albumId;
}
