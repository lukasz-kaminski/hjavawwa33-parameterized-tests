package pl.sda.spring.musicscorer.application.score;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScoreRepository extends JpaRepository<ScoreEntity, UUID> {
    List<ScoreEntity> getByAlbum_Id(UUID albumId);
}
