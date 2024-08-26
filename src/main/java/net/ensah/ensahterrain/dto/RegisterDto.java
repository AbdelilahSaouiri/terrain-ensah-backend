package net.ensah.ensahterrain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record RegisterDto(
        @NotNull(message = "Le nom d'utilisateur ne peut pas être nul.")
        @Size(min = 4, message = "Le nom d'utilisateur doit avoir au moins 4 caractères.")
        @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide.")
        String username,

        @NotNull(message = "L'année ne peut pas être nulle.")
        @Size(max = 3, message = "L'année ne peut pas dépasser 3 caractères.")
        String year,

        @NotBlank(message = "L'adresse e-mail ne peut pas être vide.")
        @Email(message = "Veuillez entrer une adresse e-mail valide.")
        String email,

        @NotBlank(message = "Le mot de passe ne peut pas être vide.")
        @Size(min = 4, max = 50, message = "Le mot de passe doit contenir entre 4 et 50 caractères.")
        String password,

        @NotBlank(message = "La confirmation du mot de passe ne peut pas être vide.")
        @Size(min = 4, max = 50, message = "La confirmation du mot de passe doit contenir entre 4 et 50 caractères.")
        String confirmPassword
) {
}
