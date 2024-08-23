package net.ensah.ensahterrain.dto;


import jakarta.validation.constraints.NotNull;

public record MatchRequestDto(@NotNull Integer MatchTime,@NotNull Integer DayNumber) {


}
