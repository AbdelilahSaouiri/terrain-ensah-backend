package net.ensah.ensahterrain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatchRequestDto(
        @NotNull @NotBlank  Integer MatchTime,
        @NotNull @NotBlank  Integer DayNumber,
        @NotNull @NotBlank String adversaire
) {


}
