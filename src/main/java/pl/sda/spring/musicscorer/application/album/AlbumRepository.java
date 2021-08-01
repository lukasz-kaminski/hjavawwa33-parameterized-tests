package pl.sda.spring.musicscorer.application.album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<AlbumEntity, UUID> {

    @Query(value = "SELECT a FROM Album a WHERE (:title is null or a.title = :title) and (:artist is null or a.artist = :artist)")
    List<AlbumEntity> findByTitleAndArtistCustom(String title, String artist);
}
