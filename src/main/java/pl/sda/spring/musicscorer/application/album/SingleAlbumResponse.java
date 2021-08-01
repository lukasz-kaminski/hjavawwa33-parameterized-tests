package pl.sda.spring.musicscorer.application.album;

import lombok.Value;
import pl.sda.spring.musicscorer.domain.Album;

import java.math.BigDecimal;

@Value
public class SingleAlbumResponse {
    String artist;
    String title;
    BigDecimal score;

    public static SingleAlbumResponse fromAlbum(Album album) {
        return null;
    }
}
