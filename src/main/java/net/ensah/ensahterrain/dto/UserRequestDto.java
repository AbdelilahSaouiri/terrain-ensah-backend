package net.ensah.ensahterrain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank @Email  String email,
        @NotBlank @Size(min = 4,max = 50) String password
) {
}
