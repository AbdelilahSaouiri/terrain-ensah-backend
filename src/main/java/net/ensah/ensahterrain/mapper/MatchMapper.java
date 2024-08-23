package net.ensah.ensahterrain.mapper;

import net.ensah.ensahterrain.dto.MatchResponseDto;
import net.ensah.ensahterrain.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    MatchResponseDto matchToMatchResponseDto(Match match);

    Match matchResponseDtoToMatch(MatchResponseDto matchResponseDto);

    List<MatchResponseDto> matchesToMatchResponseDtos(List<Match> matches);

    List<Match> matchResponseDtosToMatches(List<MatchResponseDto> matchResponseDtos);
}

