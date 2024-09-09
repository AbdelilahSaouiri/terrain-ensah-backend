package net.ensah.ensahterrain.repository;

import net.ensah.ensahterrain.entity.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MatchRepositoryTest {

    @Autowired
    private MatchRepository matchRepository;

    @BeforeEach
    void setUp() {
        matchRepository.save(Match.builder().matchTime(0).dayNumber(2).matchPlayer("abdelilah").build());
        matchRepository.save(Match.builder().matchTime(1).dayNumber(2).build());
        matchRepository.save(Match.builder().matchTime(2).dayNumber(2).build());
        matchRepository.save(Match.builder().matchTime(3).dayNumber(2).build());
    }

    @Test
    @DisplayName("test findMatchByTimeAndDay")
    void shouldFindByMatchTimeAndDayNumber() {
       Integer day=2;
       Integer matchTime=2;
       Match match=matchRepository.findByMatchTimeAndDayNumber(matchTime, day);
       assertThat(match).isNotNull();
       assertThat(match.getMatchTime()).isEqualTo(matchTime);
       assertThat(match.getDayNumber()).isEqualTo(matchTime);
    }

    @Test
    @DisplayName("test findMatchByTimeAndDay")
    void shouldNotFindByMatchTimeAndDayNumber() {
        Integer day=2;
        Integer matchTime=4;
        Match match=matchRepository.findByMatchTimeAndDayNumber(matchTime, day);
        assertThat(match).isNull();
    }

    @Test
    void shouldFindAllByDayNumber() {
        Integer day=2;
        List<Match> result = matchRepository.findAllByDayNumber(day);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(4);
        assertThat(result.getFirst()).isNotNull();
    }

    @Test
     void shouldNotFindAllByDayNumber() {
        Integer day=3;
        List<Match> result = matchRepository.findAllByDayNumber(day);
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void shouldFindByMatchPlayerAndDayNumber() {
        String matchPlayer="abdelilah";
        Integer day=2;
        Match result = matchRepository.findByMatchPlayerAndDayNumber(matchPlayer, day);
        assertThat(result).isNotNull();
        assertThat(result.getDayNumber()).isEqualTo(day);
        assertThat(result.getMatchPlayer()).isEqualTo(matchPlayer);
    }

    @Test
     void shouldNotFindByMatchPlayerAndDayNumber() {
        String matchPlayer="abdelilah";
        Integer day=3;
        Match result = matchRepository.findByMatchPlayerAndDayNumber(matchPlayer, day);
        assertThat(result).isNull();
    }
}