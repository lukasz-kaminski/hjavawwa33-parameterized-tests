package pl.sda.spring.musicscorer.application.score;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public BigDecimal getAlbumScore(String id) {
        final List<ScoreEntity> albumScores = scoreRepository.getByAlbum_Id(UUID.fromString(id));
        return calculateScore(albumScores);
    }

    private BigDecimal calculateScore(List<ScoreEntity> albumScores) {
        if(albumScores == null || albumScores.isEmpty()) {
            return BigDecimal.ZERO;
        }

        int average = albumScores.stream()
                .map(ScoreEntity::getScore)
                .reduce(0, Integer::sum)
                / albumScores.size();
        return new BigDecimal(average);

    }
}
