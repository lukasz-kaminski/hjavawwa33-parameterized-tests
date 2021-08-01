package pl.sda.spring.musicscorer.application.score;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {

    @Mock
    ScoreRepository scoreRepository;

    @InjectMocks
    ScoreService scoreService;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, 9999999})
    void shouldCalculateScoreForSingleElement(int score) {
        //given
        String id = UUID.randomUUID().toString();
        when(scoreRepository.getByAlbum_Id(UUID.fromString(id)))
                .thenReturn(List.of(new ScoreEntity(score)));
        //when
        BigDecimal albumScore = scoreService.getAlbumScore(id);

        //then
        assertThat(albumScore).isEqualTo(new BigDecimal(score));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldNotFailOnNoScores(List<ScoreEntity> scoreList) {
        //given
        String id = UUID.randomUUID().toString();
        when(scoreRepository.getByAlbum_Id(UUID.fromString(id)))
                .thenReturn(scoreList);
        //when
        BigDecimal albumScore = scoreService.getAlbumScore(id);

        //then
        assertThat(albumScore).isZero();
    }

    static Stream<List<ScoreEntity>> zeroScores() {
        return Stream.of(
                List.of(new ScoreEntity()),
                List.of(new ScoreEntity(0)),
                List.of(new ScoreEntity(), new ScoreEntity(0)),
                List.of(new ScoreEntity(0), new ScoreEntity(0))
        );
    }

    @ParameterizedTest
    @MethodSource("zeroScores")
    void shouldCalculateZeroAvg(List<ScoreEntity> scoreList) {
        //given
        String id = UUID.randomUUID().toString();
        when(scoreRepository.getByAlbum_Id(UUID.fromString(id)))
                .thenReturn(scoreList);
        //when
        BigDecimal albumScore = scoreService.getAlbumScore(id);

        //then
        assertThat(albumScore).isZero();
    }

    @ParameterizedTest
    @CsvSource({"lukasz,4", "krzychu,5", "andrzejek,2"})
    void shouldCalculateAvgFromCSV(String scorer, int score) {
        //given
        String id = UUID.randomUUID().toString();
        when(scoreRepository.getByAlbum_Id(UUID.fromString(id)))
                .thenReturn(List.of(new ScoreEntity(scorer, score)));
        //when
        BigDecimal albumScore = scoreService.getAlbumScore(id);

        //then
        assertThat(albumScore).isEqualTo(new BigDecimal(score));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv")
    void shouldCalculateAvgUsingCSVFile(String scorer, int score) {
        //given
        String id = UUID.randomUUID().toString();
        when(scoreRepository.getByAlbum_Id(UUID.fromString(id)))
                .thenReturn(List.of(new ScoreEntity(scorer, score)));
        //when
        BigDecimal albumScore = scoreService.getAlbumScore(id);

        //then
        assertThat(albumScore).isEqualTo(new BigDecimal(score));
    }


    @ParameterizedTest
    @ArgumentsSource(ScoreTestProvider.class)
    void shouldCalculateZeroAvg(List<ScoreEntity> scoreList, BigDecimal expectedResult) {
        //given
        String id = UUID.randomUUID().toString();
        when(scoreRepository.getByAlbum_Id(UUID.fromString(id)))
                .thenReturn(scoreList);
        //when
        BigDecimal albumScore = scoreService.getAlbumScore(id);

        //then
        assertThat(albumScore).isEqualTo(expectedResult);
    }


}