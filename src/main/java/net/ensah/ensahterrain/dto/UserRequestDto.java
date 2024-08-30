package net.ensah.ensahterrain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "email ne peut pas être vide")
        @NotNull(message = "email ne peut pas etre null")
        @Email(message = "entrez une adresse email valide")
        String email,
        @NotBlank(message = "mot de passe ne peut pas être vide")
        @NotNull(message = "mot de passe ne peut pas etre null")
        @Size(min = 4,max = 50,message = "La confirmation du mot de passe doit contenir entre 4 et 50 caractères.") String password
) {
}
