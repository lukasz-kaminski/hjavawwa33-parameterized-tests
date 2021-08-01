package pl.sda.spring.musicscorer.application.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumsResponse {
    List<SingleAlbumResponse> albums;
}
