package net.ensah.ensahterrain.service.Impl;

import jakarta.transaction.Transactional;
import net.ensah.ensahterrain.dto.MatchRequestDto;
import net.ensah.ensahterrain.dto.MatchResponseDto;
import net.ensah.ensahterrain.entity.Match;
import net.ensah.ensahterrain.exceptions.MatchErrorMessage;
import net.ensah.ensahterrain.exceptions.MatchException;
import net.ensah.ensahterrain.mapper.MatchMapper;
import net.ensah.ensahterrain.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MatchServiceImpl {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    public MatchResponseDto addNewMatch(MatchRequestDto matchRequestDto, Principal principal) throws MatchException {
        Match byMatchTimeAAndMatchDate = matchRepository.findByMatchTimeAndDayNumber(matchRequestDto.MatchTime(), matchRequestDto.DayNumber());
        if(byMatchTimeAAndMatchDate != null) {
            throw new MatchException(MatchErrorMessage.RECORD_ALREADY_EXISTS.getMessage());
        }
        else {
            Match newMatch = Match.builder()
                    .matchTime(matchRequestDto.MatchTime())
                    .matchDate(new Date())
                    .matchId(UUID.randomUUID().toString())
                    .matchPlayer(principal.getName())
                    .dayNumber(matchRequestDto.DayNumber())
                    .build();
            Match savedMatch = matchRepository.save(newMatch);
            return matchMapper.matchToMatchResponseDto(savedMatch);
        }
    }

    public List<MatchResponseDto> getAllMatches(Integer jour) {
        List<Match> allByMatchDate = matchRepository.findAllByDayNumber(jour);
        List<MatchResponseDto> matchResponseDtos = matchMapper.matchesToMatchResponseDtos(allByMatchDate);
        return matchResponseDtos;
    }
}
