package pl.sda.spring.musicscorer.application.album;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.sda.spring.musicscorer.domain.Album;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.sda.spring.musicscorer.application.album.AlbumType.LONGPLAY;

@SpringBootTest
class AlbumServiceTest {
    @MockBean
    AlbumRepository albumRepository;

    @Autowired
    AlbumService albumService;

    @ParameterizedTest
    @EnumSource(value = AlbumType.class, names = {".*GLE", "LONG.*"}, mode = EnumSource.Mode.MATCH_ANY)
    void shouldFindAlbumByType(AlbumType albumType) {
        //given
        when(albumRepository.findByTitleAndArtistCustom(any(), any()))
                .thenReturn(List.of(
                        new AlbumEntity(
                                UUID.randomUUID(),
                                "tytul",
                                "artysta",
                                "desc",
                                LocalDate.now(),
                                albumType
                        )
                ));
        //when
        List<Album> albumsFound = albumService.getAlbumsOfType("tytul", "artysta", albumType);
        //then
        assertThat(albumsFound.size()).isOne();

    }
}