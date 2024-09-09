package net.ensah.ensahterrain.mapper;

import net.ensah.ensahterrain.dto.MatchResponseDto;
import net.ensah.ensahterrain.entity.Match;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.*;


class MatchMapperTest {

     MatchMapper underTest= new MatchMapperImpl();
    private final String id= UUID.randomUUID().toString();

    @Test
    void matchToMatchResponseDto() {
        Match match= Match.builder().matchPlayer("abdelilah").matchTime(1).matchId(id).matchDate(new Date()).dayNumber(2).adversaire("salma")
                .id(1L)
                .build();
        MatchResponseDto expected= new MatchResponseDto(1,2,"abdelilah","salma");
        MatchResponseDto result = underTest.matchToMatchResponseDto(match);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void matchResponseDtoToMatch() {
        MatchResponseDto matchResponseDto= new MatchResponseDto(1,2,"abdelilah","salma");
        Match expected= Match.builder().matchPlayer("abdelilah").matchTime(1).dayNumber(2).adversaire("salma").build();
        Match result = underTest.matchResponseDtoToMatch(matchResponseDto);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void matchesToMatchResponseDtos() {
        Match match1= Match.builder().matchPlayer("abdelilah").matchTime(1).dayNumber(2).adversaire("salma").build();
        Match match2= Match.builder().matchPlayer("fes").matchTime(1).dayNumber(2).adversaire("test").build();
        List<Match> matchList=List.of(match1,match2);
        MatchResponseDto expected1= new MatchResponseDto(1,2,"abdelilah","salma");
        MatchResponseDto expected2= new MatchResponseDto(1,2,"fes","test");
        List<MatchResponseDto> expected = List.of(expected1, expected2);
        List<MatchResponseDto> result = underTest.matchesToMatchResponseDtos(matchList);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void matchResponseDtosToMatches() {
        MatchResponseDto result1= new MatchResponseDto(1,2,"abdelilah","salma");
        MatchResponseDto result2= new MatchResponseDto(1,2,"fes","test");
        Match expected1= Match.builder().matchPlayer("abdelilah").matchTime(1).dayNumber(2).adversaire("salma").build();
        Match expected2= Match.builder().matchPlayer("fes").matchTime(1).dayNumber(2).adversaire("test").build();
        List<Match> expected=List.of(expected1,expected2);
        List<MatchResponseDto> result = List.of(result1, result2);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}