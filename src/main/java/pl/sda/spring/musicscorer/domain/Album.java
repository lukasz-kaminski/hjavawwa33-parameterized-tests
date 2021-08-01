package pl.sda.spring.musicscorer.domain;

import lombok.Value;
import pl.sda.spring.musicscorer.application.album.AlbumType;

import java.math.BigDecimal;

@Value
public class Album {
    String artist;
    String title;
    BigDecimal score;
    AlbumType albumType;

    public Album(String artist, String title, BigDecimal score) {
        this.artist = artist;
        this.title = title;
        this.score = score;
        albumType = AlbumType.LONGPLAY;
    }

    public Album(String artist, String title, BigDecimal score, AlbumType albumType) {
        this.artist = artist;
        this.title = title;
        this.score = score;
        this.albumType = albumType;
    }
}
